package com.clonecodingproject.clone_coding_project_9_teams.service;

import com.clonecodingproject.clone_coding_project_9_teams.domain.*;
import com.clonecodingproject.clone_coding_project_9_teams.dto.PostRequestDto;
import com.clonecodingproject.clone_coding_project_9_teams.jwt.UserInfoInJwt;
import com.clonecodingproject.clone_coding_project_9_teams.repository.PostRepository;
import com.clonecodingproject.clone_coding_project_9_teams.service.user.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.*;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PostService {

    public final static String TOKEN_KEY = "Authorization";

    private final PostRepository postRepository;
    private final ImageService imageService;
    private final HttpServletRequest httpRequest;
    private final UserInfoInJwt userInfoInJwt;
    private final LoginService loginService;
    private final LikeService likeService;

    @Transactional(readOnly = true)
    public Post postDetail(Long postId) {
        return postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("해당하는 글을 찾지 못했습니다."));
    }

    @Transactional
    public void postSave(PostRequestDto postRequestDto) {
        List<ImageUrl> imageUrls = imageService.imageSave(postRequestDto.getImageUrl()); //이미지 저장
        imageService.imageUpdateToPost(   // 이미지 postId update;
                imageUrls,
                postRepository.save(  //글 저장
                        new Post(
                                postRequestDto,
                                imageUrls,
                                loginService.findByUserName(decodeTokenInUserName())
                        )
                )
        );
    }

    @Transactional
    public void postUpdate(Long postId, PostRequestDto postRequestDto) {
        Post post = postDetail(postId); // 포스트 업데이트

        if (!isUserMatchingCheck(post.getUser().getUsername())) {
            throw new IllegalArgumentException("글쓴이가 아닙니다.");
        }

        List<ImageUrl> imageUrls = imageService.ImageOverallPatch(  // 이미지 업데이트
                postId,
                postRequestDto.getImageUrl()
        );

        post.update(   //글 업데이터
                postRequestDto,
                imageUrls
        );

        imageService.imageUpdateToPost(  //이미지 연관관계 유지
                imageUrls,
                post
        );
    }

    @Transactional
    public void postDelete(Long postId) {
        if (!isUserMatchingCheck(postDetail(postId).getUser().getUsername())) {  // 글쓴이가 맞는지 체크
            throw new IllegalArgumentException("글쓴이가 아닙니다.");
        }

        imageService.imageAllDeleteByPostId(postId); //이미지 삭제
        likeService.deleteAllByPostId(postId);  // like삭제
        postRepository.deleteById(postId);  //포스트삭제
    }

    @Transactional
    public void upView(Long postId, HttpSession httpSession) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new NullPointerException("해당 게시글이 존재하지 않습니다.")
        );
        String id = Long.toString(postId);
        String newId = "[" + id + "]";

        String currentView = (String) httpSession.getAttribute("viewList");
        //        System.out.println(currentView);

        if (currentView == null) {

            httpSession.setAttribute("viewList", newId);
            int count = postRepository.findById(postId).get().getViewCount() + 1;
            post.setViewCount(count);
            postRepository.save(post);
        } else if (!currentView.contains("[" + id + "]")) {

            String newList = newId + currentView;

            httpSession.setAttribute("viewList", newList);
            int count = postRepository.findById(postId).get().getViewCount() + 1;
            post.setViewCount(count);
            postRepository.save(post);
        }
        System.out.println(currentView);
    }

    @Transactional
    public Slice<Post> getTopPost(Long page) {
        PageRequest pageRequest = PageRequest.of(Math.toIntExact(page), 5, Sort.by(Sort.Direction.DESC, "viewCount"));
        return postRepository.findAllByOrderByLikeCountDesc(pageRequest);
    }

    @Transactional
    public Slice<Post> getAllPost(Long page) {

        PageRequest pageRequest = PageRequest.of(Math.toIntExact(page), 5, Sort.by(Sort.Direction.DESC, "createdAt"));
        return postRepository.findAll(pageRequest);
    }

    @Transactional
    public Slice<Post> getRegionPost(Long page, String region) {
        PageRequest pageRequest = PageRequest.of(Math.toIntExact(page), 5, Sort.by(Sort.Direction.DESC, "createdAt"));
        return postRepository.findAllByRegion(pageRequest, region);
    }

    @Transactional
    public Slice<Post> getTopRegionPost(Long page, String region) {
        PageRequest pageRequest = PageRequest.of(Math.toIntExact(page), 5, Sort.by(Sort.Direction.DESC, "viewCount"));
        return postRepository.findAllByRegionOrderByLikeCountDesc(pageRequest, region);
    }

    private String decodeTokenInUserName() {  // 코드 디코드하는 로직
        return userInfoInJwt.getEmail_InJWT(httpRequest.getHeader(TOKEN_KEY));
    }

    private boolean isUserMatchingCheck(String username) { // 글쓴이와 로그인된 유저와 같은회원인지 체크하는 로직
        return username.equals(decodeTokenInUserName());
    }
}
package com.clonecodingproject.clone_coding_project_9_teams.service;

import com.clonecodingproject.clone_coding_project_9_teams.domain.Likes;
import com.clonecodingproject.clone_coding_project_9_teams.domain.Post;
import com.clonecodingproject.clone_coding_project_9_teams.domain.User;
import com.clonecodingproject.clone_coding_project_9_teams.repository.LikeRepository;
import com.clonecodingproject.clone_coding_project_9_teams.repository.LikesMapping;
import com.clonecodingproject.clone_coding_project_9_teams.repository.PostRepository;
import com.clonecodingproject.clone_coding_project_9_teams.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class LikeService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final LikeRepository likeRepository;

    @Transactional
    public void uplike(Long postId, String email){
        System.out.println(email);
        User user = userRepository.findByUsername(email)
                        .orElseThrow(() -> new IllegalArgumentException("찾는 회원이 없습니다."));
        Post post = postRepository.findById(postId).orElseThrow(
                ()->new NullPointerException("해당 게시글이 존재하지 않습니다.")
        );

        if (likeRepository.findByUserAndPost(user, post)==null){
            Likes likes = new Likes(user, post);
            likeRepository.save(likes);
        } else {
            Likes likes = likeRepository.getLikesByUserAndPost(user, post);
            likeRepository.delete(likes);
        }

        int count = likeRepository.findAllByPost(post).size();
        post.setLikeCount(count);
        postRepository.save(post);

    }
    @Transactional
    public boolean checkLike(Long postId, String email){
        User user = userRepository.findByUsername(email)
                .orElseThrow(() -> new IllegalArgumentException("찾는 회원이 없습니다."));
        Post post = postRepository.findById(postId).orElseThrow(
                ()->new NullPointerException("해당 게시글이 존재하지 않습니다.")
        );
        if (likeRepository.findByUserAndPost(user, post)==null){
            return true;
        }else {
            return false;
        }
    }

    @Transactional
    public List<LikesMapping> getLikes(String email){
        User user = userRepository.findByUsername(email)
                .orElseThrow(() -> new IllegalArgumentException("찾는 회원이 없습니다."));

        return likeRepository.findByUser(user);
    }

    public void deleteAllByPostId(Long postId) {
        likeRepository.deleteAllByPostId(postId);
    }
}

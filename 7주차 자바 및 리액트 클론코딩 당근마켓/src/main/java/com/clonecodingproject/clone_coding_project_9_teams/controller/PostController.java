package com.clonecodingproject.clone_coding_project_9_teams.controller;

import com.clonecodingproject.clone_coding_project_9_teams.domain.Post;
import com.clonecodingproject.clone_coding_project_9_teams.domain.resultType.Update;
import com.clonecodingproject.clone_coding_project_9_teams.dto.PostRequestDto;
import com.clonecodingproject.clone_coding_project_9_teams.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Slice;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.*;

@RequiredArgsConstructor
@RestController
public class PostController {

    private final PostService postService;

    @GetMapping("/post/detail/{postId}")
    public Post postDetail(@PathVariable Long postId, HttpSession httpSession) {
        postService.upView(postId, httpSession);
        return postService.postDetail(postId);
    }

    @PostMapping("/post")
    public void postSave(@RequestBody PostRequestDto postRequestDto) {
        postService.postSave(postRequestDto);
    }

    @PatchMapping("/post/{postId}")
    public void postUpdate(@PathVariable Long postId,
                           @Validated(Update.class) @RequestBody PostRequestDto postRequestDto) {
        postService.postUpdate(postId, postRequestDto);
    }

    @DeleteMapping("/post/{postId}")
    public void postDelete(@PathVariable Long postId){
        postService.postDelete(postId);
    }

    // 비로그인유저 전체 매물 조회
    @GetMapping("/post/all/region")
    public Slice<Post> getPosts(HttpServletRequest httpServletRequest){
        Long page = Long.parseLong(httpServletRequest.getParameter("page"));
        return postService.getAllPost(page);
    }

    // 비로그인유저 인기 매물 조회

    @GetMapping("/post/top/all/region")
    public Slice<Post> getTopPost(HttpServletRequest httpServletRequest){
        Long page = Long.parseLong(httpServletRequest.getParameter("page"));
        return postService.getTopPost(page);
    }

    // 로그인유저 지역 매물 조회
    @GetMapping("/post/{region}")
    public Slice<Post> getRegionPost(HttpServletRequest httpServletRequest, @PathVariable String region) {
        Long page = Long.parseLong(httpServletRequest.getParameter("page"));
        return postService.getRegionPost(page, region);
    }

    // 로그인유저 인기 지역 매물 조회
    @GetMapping("/post/top/{region}")
    public Slice<Post> getTopRegionPost(HttpServletRequest httpServletRequest, @PathVariable String region) {
        Long page = Long.parseLong(httpServletRequest.getParameter("page"));
        return postService.getTopRegionPost(page, region);
    }
}

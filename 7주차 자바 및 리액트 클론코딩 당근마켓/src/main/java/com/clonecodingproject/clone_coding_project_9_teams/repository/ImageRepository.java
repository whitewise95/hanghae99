package com.clonecodingproject.clone_coding_project_9_teams.repository;

import com.clonecodingproject.clone_coding_project_9_teams.domain.ImageUrl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ImageRepository extends JpaRepository<ImageUrl, Long> {
    List<ImageUrl> findAllByPostId(Long postId);

    void deleteByPostId(Long postId);


}

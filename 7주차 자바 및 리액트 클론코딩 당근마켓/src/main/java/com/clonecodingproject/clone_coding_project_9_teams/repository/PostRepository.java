package com.clonecodingproject.clone_coding_project_9_teams.repository;

import com.clonecodingproject.clone_coding_project_9_teams.domain.Post;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    Slice<Post> findAllByOrderByLikeCountDesc(PageRequest pageRequest);

    Slice<Post> findAllByRegion(PageRequest pageRequest, @Param("region") String region);

    Slice<Post> findAllByRegionOrderByLikeCountDesc(PageRequest pageRequest, @Param("region") String region);
//
//    @Query("select p " + "from Post p " + "order by p.id desc ")
//    Slice<Post> findByOrderByIdDescWithList(Pageable pageable);
//
//    @Query("select p " + "from Post p " +"where p.id < :postId " + "order by p.id desc " )
//    Slice<Post> findByOrderByIdDesc(Long lastPostId, Pageable pageable);




}

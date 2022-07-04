package com.clonecodingproject.clone_coding_project_9_teams.domain;

import com.clonecodingproject.clone_coding_project_9_teams.dto.ImageRequestDto;
import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class ImageUrl {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long postId;

    @Column(nullable = false)
    private String imageUrl;

    public ImageUrl(ImageRequestDto imageRequestDto) {
        this.imageUrl = imageRequestDto.getImageUrl();
    }

    public void updateToPost(Post post) {
        this.postId = post.getId();
    }
}

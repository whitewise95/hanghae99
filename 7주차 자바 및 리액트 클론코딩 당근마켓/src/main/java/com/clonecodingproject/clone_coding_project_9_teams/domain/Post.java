package com.clonecodingproject.clone_coding_project_9_teams.domain;

import com.clonecodingproject.clone_coding_project_9_teams.domain.resultType.Timestamped;
import com.clonecodingproject.clone_coding_project_9_teams.dto.PostRequestDto;
import lombok.*;

import javax.persistence.*;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Post extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String category;

    @Column(nullable = false)
    private int likeCount;

    @Column(nullable = false)
    private String region;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private int price;

    @Column(nullable = false)
    private int viewCount;

    @OneToMany
    @JoinColumn(name = "imageUrlId")
    private List<ImageUrl> imageUrl;

    public Post(PostRequestDto postRequestDto, List<ImageUrl> imageUrls, User user) {
        this.user = user;
        this.title = postRequestDto.getTitle();
        this.category = postRequestDto.getCategory();
        this.region = user.getRegion();
        this.likeCount = 0;
        this.viewCount = 0;
        this.content = postRequestDto.getContent();
        this.price = postRequestDto.getPrice();
        this.imageUrl = imageUrls;
    }

    public void update(PostRequestDto postRequestDto, List<ImageUrl> imageUrls) {
        this.title = postRequestDto.getTitle();
        this.category = postRequestDto.getCategory();
        this.content = postRequestDto.getContent();
        this.price = postRequestDto.getPrice();
        this.imageUrl = imageUrls;
    }
}

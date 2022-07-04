package com.clonecodingproject.clone_coding_project_9_teams.dto;

import com.clonecodingproject.clone_coding_project_9_teams.domain.resultType.Update;
import lombok.*;

import javax.validation.constraints.NotEmpty;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class PostRequestDto {

    @NotEmpty(groups = { Update.class }, message = "title 은 반드시 존재해야합니다.")
    private String title;

    @NotEmpty(groups = { Update.class }, message = "category 는 반드시 존재해야합니다.")
    private String category;

    @NotEmpty(groups = { Update.class }, message = "content 는 반드시 존재해야합니다.")
    private String content;

    @NotEmpty(groups = { Update.class }, message = "price 는 반드시 존재해야합니다.")
    private int price;

    private List<ImageRequestDto> imageUrl;
}

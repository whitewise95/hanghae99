package com.clonecodingproject.clone_coding_project_9_teams.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class LoginResDto {
    private String nickname;
    private String region;
    private String accessToken;
    private String errorMessage;
}

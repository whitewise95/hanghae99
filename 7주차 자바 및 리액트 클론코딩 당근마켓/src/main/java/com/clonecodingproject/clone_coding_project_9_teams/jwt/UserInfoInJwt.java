package com.clonecodingproject.clone_coding_project_9_teams.jwt;

import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@RequiredArgsConstructor
@Component
public class UserInfoInJwt {


    private final JwtTokenProvider jwtTokenProvider;

    public String getEmail_InJWT(String authorization){
        String accessToken = authorization.substring(7);
        Claims accessClaims = jwtTokenProvider.getClaimsFormToken(accessToken);
        return (String) accessClaims.get("email");
    }
}

package com.clonecodingproject.clone_coding_project_9_teams.jwt;

import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.xml.bind.DatatypeConverter;
import java.util.Base64;
import java.util.Date;

@RequiredArgsConstructor
@Component
public class JwtTokenProvider {

    private String SECRET_KEY = "sec";
    private final long ACCESS_TOKEN_VALID_TIME = 60 * 60 * 24 * 7 * 1000L;   // 1분


    @PostConstruct
    protected void init() {
        System.out.println("SECRET_KEY: " + SECRET_KEY);
        SECRET_KEY = Base64.getEncoder().encodeToString(SECRET_KEY.getBytes());
    }

    public String createAccessToken(String username) {
        Claims claims = Jwts.claims();//.setSubject(userPk); // JWT payload 에 저장되는 정보단위
        claims.put("email", username);
        Date now = new Date();
        return Jwts.builder()
                .setClaims(claims) // 정보 저장
                .setIssuedAt(now) // 토큰 발행 시간 정보
                .setExpiration(new Date(now.getTime() + ACCESS_TOKEN_VALID_TIME)) // set Expire Time
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY) // 사용할 암호화 알고리즘과
                .compact();
    }


    //AccessToken 유효성 검사
    public boolean isValidAccessToken(String token) {
        System.out.println("isValidToken is : " +token);
        try {
            Claims accessClaims = getClaimsFormToken(token);
            System.out.println("Access expireTime: " + accessClaims.getExpiration());
            System.out.println("Access email: " + accessClaims.get("email"));
            return true;
        } catch (ExpiredJwtException exception) {
            System.out.println("Token Expired email : " + exception.getClaims().get("email"));
            return false;
        } catch (JwtException exception) {
            System.out.println("Token Tampered");
            return false;
        }
    }

    //JWT 구문분석 함수
    public Claims getClaimsFormToken(String token) {
        return Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(SECRET_KEY))
                .parseClaimsJws(token)
                .getBody();
    }
}

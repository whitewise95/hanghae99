package com.clonecodingproject.clone_coding_project_9_teams.interceptor;

import com.clonecodingproject.clone_coding_project_9_teams.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


//가로챈 요청에서 인증이 되어있는지(로그인 되어있는지)판단 후 각각의 알맞은 경우에 따라 응답 메시지 작성
//토큰이 유효하면 요청을 컨트롤러에게 넘겨 줌
@Component
@RequiredArgsConstructor
public class JwtTokenInterceptor implements HandlerInterceptor {

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {

        System.out.println("JwtToken 호출");
        String authorization = request.getHeader("Authorization");
        System.out.println("Authorization:" + authorization);

        //authorization 비어있다면
        if(authorization == null){
            response.setStatus(401);
//        response.setHeader("REFRESH_TOKEN", refreshToken);
            response.setHeader("msg", "Authorization is null");
            return false;
        }

        //Autorization 헤더 값이 비어있지 않고, Bearer으로 시작한다면 유효성 검사
        if (authorization.startsWith("Bearer ")) {
            String accessToken = authorization.substring(7);
            if (jwtTokenProvider.isValidAccessToken(accessToken)) {
                return true;
            }
        }

        response.setStatus(401);
        response.setHeader("Authorization", authorization);
//        response.setHeader("REFRESH_TOKEN", refreshToken);
        response.setHeader("msg", "Check the tokens.");
        return false;
    }
}

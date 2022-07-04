package com.clonecodingproject.clone_coding_project_9_teams.controller;

import com.clonecodingproject.clone_coding_project_9_teams.dto.LoginDto;
import com.clonecodingproject.clone_coding_project_9_teams.dto.LoginResDto;
import com.clonecodingproject.clone_coding_project_9_teams.dto.SignupDto;
import com.clonecodingproject.clone_coding_project_9_teams.dto.SignupResDto;
import com.clonecodingproject.clone_coding_project_9_teams.jwt.UserInfoInJwt;
import com.clonecodingproject.clone_coding_project_9_teams.service.user.LoginService;
import com.clonecodingproject.clone_coding_project_9_teams.service.user.SignupService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final SignupService userService;
    private final LoginService loginService;
    private final UserInfoInJwt userInfoInJwt;


    //회원가입 요청 처리
    @PostMapping("/user/signup")
    public SignupResDto signUp(@RequestBody SignupDto signupDto) throws Exception {
        return userService.signUp(signupDto);
    }

    //로그인 요청
    @PostMapping("/user/login")
    public LoginResDto login(@RequestBody LoginDto loginDto) throws Exception {
        return loginService.login(loginDto);
    }

    @GetMapping("/test")
    public String test(){
        return "ok";
    }
}

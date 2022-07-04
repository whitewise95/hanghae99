package com.clonecodingproject.clone_coding_project_9_teams.service.user;

import com.clonecodingproject.clone_coding_project_9_teams.domain.User;
import com.clonecodingproject.clone_coding_project_9_teams.dto.*;
import com.clonecodingproject.clone_coding_project_9_teams.error.ErrorCode;
import com.clonecodingproject.clone_coding_project_9_teams.error.exceptions.LoginException;
import com.clonecodingproject.clone_coding_project_9_teams.jwt.JwtTokenProvider;
import com.clonecodingproject.clone_coding_project_9_teams.jwt.encoder.SHA256;
import com.clonecodingproject.clone_coding_project_9_teams.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class LoginService {

    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;

    public LoginResDto login(LoginDto loginDto) throws NoSuchAlgorithmException {

        //로그인 정보에 맞는 유저 찾기
        Optional<User> user = userRepository.findByUsernameAndPassword(loginDto.getUsername(), SHA256.encrypt(loginDto.getPassword()));

        //아이디 비번 틀렸을 시
        if (!user.isPresent()) {
            throw new LoginException("이메일 또는 패스워드가 틀렸습니다.", ErrorCode.LOGIN_EXCEPTION);
        }

        String accessToken = jwtTokenProvider.createAccessToken(user.get().getUsername());
        return LoginResDto.builder()
                .accessToken(accessToken)
                .nickname(user.get().getNickname())
                .region(user.get().getRegion())
                .build();
    }

    public User findByUserName(String authorization) {
        return userRepository.findByUsername(authorization)
                .orElseThrow(() -> new IllegalArgumentException("찾는 회원이 없습니다."));
    }
}

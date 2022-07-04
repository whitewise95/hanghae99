package com.clonecodingproject.clone_coding_project_9_teams.validator;

import com.clonecodingproject.clone_coding_project_9_teams.dto.SignupDto;
import com.clonecodingproject.clone_coding_project_9_teams.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;
@RequiredArgsConstructor
@Component
public class UserValidator {

    private final UserRepository userRepository;

    //입력 받은 값 체크 함수
    public void checkValues(SignupDto signupDto) {

        String username = signupDto.getUsername();
        String password = signupDto.getPassword();
        String nickname = signupDto.getNickname();

        if(username.isEmpty() || password.isEmpty()){
            throw new IllegalArgumentException("이메일 혹은 비밀번호가 공란입니다. 다시 입력해주세요.");
        }else if(!checkEmail(username)){
            throw new IllegalArgumentException("이메일 형식이 맞지 않습니다.");
        }else if(!checkPassword(password)){
            throw new IllegalArgumentException("비밀번호는 8 ~ 10자 영문, 숫자 및 특수문자조합으로 작성해주세요.");
        }else if(!checkNickname(nickname)){
            throw new IllegalArgumentException("닉네임은 3 ~ 8자 한글, 영문, 숫자로 작성해주세요");
        }
    }

    //이메일 중복 체크 함수
    public Boolean checkDupeEmail(SignupDto signupDto){
        return userRepository.existsByUsername(signupDto.getUsername());
    }

    //닉네임 중복 체크 함수
    public Boolean checkDupeNickname(SignupDto signupDto){
        return userRepository.existsByNickname(signupDto.getNickname());
    }

/*-------------------------------------<입력값 형식 체크 함수>----------------------------------------*/

    //이메일 형식 체크 함수
    private boolean checkEmail(String username){
        String pattern = "\\w+@\\w+\\.\\w+(\\.\\W+)?";
        return Pattern.matches(pattern, username);
    }
    //패스워드 형식 체크
    private boolean checkPassword(String password){
        String pattern = "^(?=.*\\d)(?=.*[a-zA-Z])(?=.*[@$!%*#?&])[0-9a-zA-Z@$!%*#?&]{3,10}$";
        return Pattern.matches(pattern, password);
    }
    //닉네임 형식 체크
    private boolean checkNickname(String nickname){
        String pattern = "^([ㄱ-ㅎ]|[가-힣]|[a-z]|[A-Z]|[0-9]){3,10}$";
        return Pattern.matches(pattern, nickname);
    }
}

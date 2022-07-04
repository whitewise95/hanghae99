package com.clonecodingproject.clone_coding_project_9_teams.service.user;

import com.clonecodingproject.clone_coding_project_9_teams.domain.User;
import com.clonecodingproject.clone_coding_project_9_teams.dto.SignupDto;
import com.clonecodingproject.clone_coding_project_9_teams.dto.SignupResDto;
import com.clonecodingproject.clone_coding_project_9_teams.error.ErrorCode;
import com.clonecodingproject.clone_coding_project_9_teams.error.exceptions.EmailDubplicateException;
import com.clonecodingproject.clone_coding_project_9_teams.error.exceptions.NicknameDubplicateException;
import com.clonecodingproject.clone_coding_project_9_teams.jwt.encoder.SHA256;
import com.clonecodingproject.clone_coding_project_9_teams.repository.UserRepository;
import com.clonecodingproject.clone_coding_project_9_teams.validator.UserValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;

@RequiredArgsConstructor
@Service
public class SignupService {

    private final UserRepository userRepository;
    private final UserValidator userValidator;
    public SignupResDto signUp(SignupDto signupDto) throws NoSuchAlgorithmException {
        SignupResDto signupResDto = new SignupResDto();
        //입력 받은 값 체크
        userValidator.checkValues(signupDto);

        //등록된 유저 중 이메일 중복 체크
        if(userValidator.checkDupeEmail(signupDto)){
            throw new EmailDubplicateException("이메일이 중복되었습니다.", ErrorCode.EMAIL_DUBP_EXCEPTION);

        //등록된 유저 중 닉네임 중복 체크
        }else if (userValidator.checkDupeNickname(signupDto)) {
            throw new NicknameDubplicateException("닉네임이 중복되었습니다.", ErrorCode.NICKNAME_DUBP_EXCEPTION);

        //위의 모든 예외처리를 통과하면 모든 비밀번호 암호화 후 유저 등록
        }else{
            String encodedPw = SHA256.encrypt(signupDto.getPassword());
            User user = new User(signupDto, encodedPw);
            userRepository.save(user);
            signupResDto.setMessage("회원가입을 성공했습니다!");
        }
        return signupResDto;
    }
}

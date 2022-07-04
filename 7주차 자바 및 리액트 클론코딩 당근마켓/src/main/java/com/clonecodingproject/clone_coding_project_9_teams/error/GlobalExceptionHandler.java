package com.clonecodingproject.clone_coding_project_9_teams.error;

import com.clonecodingproject.clone_coding_project_9_teams.error.exceptions.EmailDubplicateException;
import com.clonecodingproject.clone_coding_project_9_teams.error.exceptions.LoginException;
import com.clonecodingproject.clone_coding_project_9_teams.error.exceptions.NicknameDubplicateException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice   //모든 RestController에서 발생한 예외를 처리
public class GlobalExceptionHandler {

    //@ExceptionHandler는?
    // -> @Controller, @RestController로 등록된 빈 내에서 발생하는 예외를 잡아서 하나의 메서드에서 처리해주는 어노테이션
    //- @ExceptionHandler(xxxException.class) -> xxxException을 인자로 캐치하고 싶다는 의미, xxxException은 커스텀한 예외 클래스
    //- @ExceptionHandler({ Exception1.class, Exception2.class})와 같이 두개 이상도 가능

    //이메일 중복 예외처리
    @ExceptionHandler(EmailDubplicateException.class)
    public ResponseEntity<ErrorResponse> handleEmailDubplicateException(EmailDubplicateException ex){
        ErrorResponse response = new ErrorResponse(ex.getErrorCode());
        return new ResponseEntity<>(response, HttpStatus.valueOf(ex.getErrorCode().getStatus()));
    }

    //닉네임 중복 예외처리
    @ExceptionHandler(NicknameDubplicateException.class)
    public ResponseEntity<ErrorResponse> handleNicknameDupblicateException(NicknameDubplicateException ex){
        ErrorResponse response = new ErrorResponse(ex.getErrorCode());
        return new ResponseEntity<>(response, HttpStatus.valueOf(ex.getErrorCode().getStatus()));
    }


    //로그인 실패 예외처리
    @ExceptionHandler(LoginException.class)
    public ResponseEntity<ErrorResponse> handleLoginException(LoginException ex){
        ErrorResponse response = new ErrorResponse(ex.getErrorCode());
        return new ResponseEntity<>(response, HttpStatus.valueOf(ex.getErrorCode().getStatus()));
    }
}

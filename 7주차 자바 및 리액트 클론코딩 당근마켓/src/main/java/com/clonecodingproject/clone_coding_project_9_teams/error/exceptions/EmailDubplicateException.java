package com.clonecodingproject.clone_coding_project_9_teams.error.exceptions;

import com.clonecodingproject.clone_coding_project_9_teams.error.ErrorCode;
import lombok.Getter;


//굳이 이렇게 EmilDupblicateException와 NicknameDubplicateException을 클래스를 따로 생성하지 않고
//RuntimeException을 상속받는 SignupException클래스를 생성해서 내부에 함수를 두개 두는것이 좋은 방법인 것 같다. -> 시도해 봤지만 실패
//RuntimeException을 상속받는 클래스의 네임과 동일한 메서드를 가지고 있어야 하는듯
//RuntimeException은 실행시 발생하는 오류에 관한 class
@Getter
public class EmailDubplicateException extends RuntimeException{

    private final ErrorCode errorCode;

    public EmailDubplicateException(String message, ErrorCode errorCode){
        super(message);
        this.errorCode = errorCode;
    }
}

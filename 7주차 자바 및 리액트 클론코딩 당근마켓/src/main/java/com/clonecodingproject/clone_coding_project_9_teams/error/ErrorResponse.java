package com.clonecodingproject.clone_coding_project_9_teams.error;

import lombok.Getter;
import lombok.Setter;

//ResponseEntity의 파라미터 타입이 될 예정 -> 실질적으로 예외처리시 응답메시지 바디에 작성될 형식
@Getter
@Setter
public class ErrorResponse {
    private int status;
    private String message;

    public ErrorResponse(ErrorCode errorCode){
        this.status = errorCode.getStatus();
        this.message = errorCode.getMessage();
    }
}

package com.sparta.limited.common_module.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class BusinessException extends RuntimeException {

    private final String overrideMessage;
    private ErrorCode errorCode;

    public BusinessException(ErrorCode errorCode, String overrideMessage) {
        super(overrideMessage);
        this.errorCode = errorCode;
        this.overrideMessage = overrideMessage;
    }

    @Override
    public String getMessage() {
        return overrideMessage != null ? overrideMessage : errorCode.getMessage();
    }


    public HttpStatus getHttpStatus() {
        return errorCode.getHttpStatus();
    }
}

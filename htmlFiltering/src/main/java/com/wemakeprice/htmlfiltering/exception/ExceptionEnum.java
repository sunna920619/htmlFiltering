package com.wemakeprice.htmlfiltering.exception;

import org.springframework.http.HttpStatus;

public enum ExceptionEnum {

    INPUT_URL_IS_EMPTY(HttpStatus.BAD_REQUEST, "E0001", "URL을 입력해야 합니다."),
    INPUT_URL_IS_WRONG(HttpStatus.BAD_REQUEST, "E0002", "잘못된 Url 입니다."),
    TYPE_IS_EMPTY(HttpStatus.BAD_REQUEST, "E0003", "Type을 입력해야 합니다."),
    TYPE_IS_WRONG(HttpStatus.BAD_REQUEST, "E0004", "존재하지 않는 Type이 입력되었습니다."),
    DIVISOR_IS_ZERO(HttpStatus.BAD_REQUEST, "E0005", "출력 단위 묶음은 1 이상으로 입력해야 합니다.");
	
    private final HttpStatus status;
    private final String code;
    private String message;

    ExceptionEnum(HttpStatus status, String code) {
        this.status = status;
        this.code = code;
    }

    ExceptionEnum(HttpStatus status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public String getCode() {
		return code;
	}
}

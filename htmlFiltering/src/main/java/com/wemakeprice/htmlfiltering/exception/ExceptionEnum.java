package com.wemakeprice.htmlfiltering.exception;

import org.springframework.http.HttpStatus;

public enum ExceptionEnum {

    INPUT_URL_IS_EMPTY(HttpStatus.BAD_REQUEST, "E0001", "URL�� �Է��ؾ� �մϴ�."),
    INPUT_URL_IS_WRONG(HttpStatus.BAD_REQUEST, "E0002", "�߸��� URL�Դϴ�."),
    TYPE_IS_EMPTY(HttpStatus.BAD_REQUEST, "E0003", "Type�� �Է��ؾ� �մϴ�."),
    TYPE_IS_WRONG(HttpStatus.BAD_REQUEST, "E0004", "�������� �ʴ� Type�� �ԷµǾ����ϴ�."),
    DIVISOR_IS_ZERO(HttpStatus.BAD_REQUEST, "E0005", "��� ���� ������ 1 �̻����� �Է��ؾ� �մϴ�."),
	URL_CONNECTION_FAILED(HttpStatus.NOT_FOUND, "E0006", "�Էµ� URL�� ���� �� connection ������ �߻��߽��ϴ�.");
	
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

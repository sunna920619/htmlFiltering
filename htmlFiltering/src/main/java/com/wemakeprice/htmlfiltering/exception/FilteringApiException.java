package com.wemakeprice.htmlfiltering.exception;

public class FilteringApiException extends RuntimeException {
    private ExceptionEnum error;

    public FilteringApiException(ExceptionEnum e) {
        super(e.getMessage());
        this.error = e;
    }
    
    public ExceptionEnum getError() {
    	return error;
    }
}

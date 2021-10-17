package com.wemakeprice.htmlfiltering.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionAdvice {
    @ExceptionHandler({FilteringApiException.class})
    public ResponseEntity<ExceptionEntity> exceptionHandler(HttpServletRequest request, final FilteringApiException e) {
        return ResponseEntity
                .status(e.getError().getStatus())
                .body(new ExceptionEntity(e.getError().getCode(), e.getError().getMessage()));
    }

}

package com.kjug.boottask;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
@ControllerAdvice // advice
public class ControllerErrorHandler {
    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<String> handleNullPointerException(){
        var npeResponse = ResponseEntity.internalServerError()
                .body("request failed,null pointer exception, ensure value is not 'test'");
        return npeResponse;
    }
}

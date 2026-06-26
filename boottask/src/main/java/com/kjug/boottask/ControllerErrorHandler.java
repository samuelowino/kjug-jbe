package com.kjug.boottask;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

@ControllerAdvice
public class ControllerErrorHandler {
    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<String> handleNullPointerException(){
        var npeResponse = ResponseEntity.internalServerError()
                .body("request failed,null pointer exception, ensure value is not 'test'");
        return npeResponse;
    }
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<String> handleContraintViolation(
            ConstraintViolationException ex
    ) {
        return ResponseEntity.badRequest()
                .body("invalid request:" + ex.getLocalizedMessage());
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex
    ) {
        return ResponseEntity
                .badRequest()
                .body("invalid request body parameters: " + ex.getLocalizedMessage());
    }
    @ExceptionHandler(HandlerMethodValidationException.class)
    public ResponseEntity<String> handleMethodValidationException(
            HandlerMethodValidationException ex
    ) {
        return ResponseEntity
                .badRequest()
                .body("invalid request headers: " + ex.getLocalizedMessage());
    }

}

package com.example.api.exception;

import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ApiError> onRuntimeException(RuntimeException ex) {
        ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error", ex);
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public ResponseEntity<ApiError> onMethodNotSupportedException(HttpRequestMethodNotSupportedException ex) {
        ApiError apiError = new ApiError(HttpStatus.METHOD_NOT_ALLOWED, "Method Not Allowed", ex);
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(FeignException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ApiError> onFeignException(FeignException ex) {
        ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error", ex);
        return buildResponseEntity(apiError);
    }

    private ResponseEntity<ApiError> buildResponseEntity(ApiError apiError) {
        return ResponseEntity
                .status(apiError.getStatus())
                .body(apiError);
    }
}

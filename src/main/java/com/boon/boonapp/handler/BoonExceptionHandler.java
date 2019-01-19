package com.boon.boonapp.handler;

import com.boon.boonapp.exception.AuthorizationException;
import com.boon.boonapp.exception.BoonDataValidationException;
import com.boon.boonapp.exception.NotFoundBaseException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.nio.file.AccessDeniedException;

@Slf4j
@ControllerAdvice
public class BoonExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = NotFoundBaseException.class)
    public ResponseEntity<Object> handleNotFoundException(Exception ex, WebRequest request) {
        return handleException(ex, HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(value = { Exception.class })
    public ResponseEntity<Object> handleBasicException(Exception ex, WebRequest request) {
        return handleException(ex, HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    @ExceptionHandler(value = { AccessDeniedException.class, AuthorizationException.class })
    public ResponseEntity<Object> handleAccessDeniedException(Exception ex, WebRequest request) {
        return handleException(ex, HttpStatus.UNAUTHORIZED, request);
    }

    @ExceptionHandler(value = { IllegalArgumentException.class, UnsupportedOperationException.class, BoonDataValidationException.class})
    public ResponseEntity<Object> handleBadRequestException(Exception ex, WebRequest request) {
        log.error("Request={}, Error={}", request ,ex);
        return handleException(ex, HttpStatus.BAD_REQUEST, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return handleException(ex, HttpStatus.BAD_REQUEST, request);
    }

    private ResponseEntity<Object> handleException(Exception ex, HttpStatus httpStatus, WebRequest webRequest) {
        log.error("[{}]: Message={}.", httpStatus.value(), ex.getMessage(), ex);
        ErrorResponse errorResponse = ErrorResponse.builder()
                .message(ex.getMessage())
                .httpResponseCode(httpStatus.value())
                .build();
        return handleExceptionInternal(ex, errorResponse, new HttpHeaders(), httpStatus, webRequest);
    }
}

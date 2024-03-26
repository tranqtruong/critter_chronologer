package com.trg.critter_chronologer.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.trg.critter_chronologer.exception.PetException;
import com.trg.critter_chronologer.exception.ScheduleException;
import com.trg.critter_chronologer.exception.UserException;
import com.trg.critter_chronologer.payload.error.ApiError;

import jakarta.validation.ConstraintViolationException;

@ControllerAdvice
public class ExceptionController extends ResponseEntityExceptionHandler{
    @Override
    @Nullable
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        List<String> errors = ex.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.toList());
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST.value(), "Validation failed", errors);
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiError> handleConstraintViolationException(ConstraintViolationException exception, WebRequest request) {
        List<String> errors = exception.getConstraintViolations().stream()
               .map(error -> error.getMessage())
               .collect(Collectors.toList());
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST.value(), "Validation failed", errors);
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserException.class)
    public ResponseEntity<ApiError> handleUserNotFoundException(UserException exception, WebRequest request) {
        ApiError apiError = new ApiError(exception.getHttpStatus().value(), exception.getMessage(), null);
        return new ResponseEntity<>(apiError, exception.getHttpStatus());
    }

    @ExceptionHandler(PetException.class)
    public ResponseEntity<ApiError> handlePetNotFoundException(PetException exception, WebRequest request) {
        ApiError apiError = new ApiError(exception.getHttpStatus().value(), exception.getMessage(), null);
        return new ResponseEntity<>(apiError, exception.getHttpStatus());
    }

    @ExceptionHandler(ScheduleException.class)
    public ResponseEntity<ApiError> handleScheduleNotFoundException(ScheduleException exception, WebRequest request) {
        ApiError apiError = new ApiError(exception.getHttpStatus().value(), exception.getMessage(), null);
        return new ResponseEntity<>(apiError, exception.getHttpStatus());
    }
}

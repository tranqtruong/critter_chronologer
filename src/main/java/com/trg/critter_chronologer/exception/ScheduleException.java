package com.trg.critter_chronologer.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ScheduleException extends RuntimeException{
    private HttpStatus httpStatus;
    public ScheduleException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }
}

package com.trg.critter_chronologer.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class PetException extends RuntimeException {
    private HttpStatus httpStatus;

    public PetException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }
}

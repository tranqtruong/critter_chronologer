package com.trg.critter_chronologer.payload.error;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class ApiError {
    private int status;
    private String message;
    private List<String> errors;
}

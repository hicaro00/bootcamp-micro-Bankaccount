package com.lizana.microservicebankaccount.application.CustomExeption;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


public class InvalidPatternTypeException extends RuntimeException {
    public InvalidPatternTypeException(String message) {
        super(message);
    }
}

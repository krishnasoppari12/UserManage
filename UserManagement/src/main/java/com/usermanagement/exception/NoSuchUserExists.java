package com.usermanagement.exception;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class NoSuchUserExists extends RuntimeException{
    private String message;
}

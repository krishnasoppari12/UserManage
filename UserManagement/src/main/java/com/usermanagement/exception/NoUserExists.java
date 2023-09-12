package com.usermanagement.exception;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class NoUserExists extends RuntimeException{
    private String message;
   /* public UserExists(String fullName) {
        super("User not found with full name: " + fullName);
    }*/
}

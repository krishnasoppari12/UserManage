package com.usermanagement.exception;

/*@AllArgsConstructor
@NoArgsConstructor*/
public class UserAlreadyExists extends RuntimeException{
    //private String message;
    public UserAlreadyExists(String fullName) {
        super(fullName);
    }
}

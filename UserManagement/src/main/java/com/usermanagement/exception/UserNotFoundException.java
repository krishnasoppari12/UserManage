package com.usermanagement.exception;

public class UserNotFoundException extends RuntimeException{

	 public UserNotFoundException(String fullName) {
	        super("User not found with full name: " + fullName);
	    }

}

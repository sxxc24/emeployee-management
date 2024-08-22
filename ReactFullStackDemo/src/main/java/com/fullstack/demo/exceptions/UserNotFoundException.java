package com.fullstack.demo.exceptions;

public class UserNotFoundException extends RuntimeException {

	public UserNotFoundException(long id) {
		super("Could not found user with id : " + id);
	}
}

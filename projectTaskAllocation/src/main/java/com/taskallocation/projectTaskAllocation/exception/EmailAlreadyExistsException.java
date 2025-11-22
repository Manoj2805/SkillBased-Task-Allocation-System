package com.taskallocation.projectTaskAllocation.exception;

public class EmailAlreadyExistsException extends Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    //Email already exists 
	public EmailAlreadyExistsException(String message) {
        super(message);
    }
}
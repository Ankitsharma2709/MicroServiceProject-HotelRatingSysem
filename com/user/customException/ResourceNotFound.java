package com.user.customException;

public class ResourceNotFound extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public ResourceNotFound() {
		super("Resource not found onserver !!");
	}
	
	public ResourceNotFound(String message) {
		super(message);
	}
	

}

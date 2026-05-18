package com.tonin.animaltrack.model;

public class CheckATException extends Exception {
	
	public CheckATException() {
		
	}
	
	public CheckATException(String message) {
		super(message);
	}
	
	public CheckATException(String message, Throwable cause) {
		super(message, cause);
	}

}

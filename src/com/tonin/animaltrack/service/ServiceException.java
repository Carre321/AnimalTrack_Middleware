package com.tonin.animaltrack.service;

public class ServiceException extends CheckATException {

	public ServiceException() {
		
	}
	
	public ServiceException(String message) {
		super(message);
	}
	
	public ServiceException(String message, Throwable cause) {
		super(message, cause);
	}
}

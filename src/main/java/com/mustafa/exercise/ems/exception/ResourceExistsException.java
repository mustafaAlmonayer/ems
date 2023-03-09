package com.mustafa.exercise.ems.exception;

public class ResourceExistsException extends RuntimeException {

	private static final long serialVersionUID = -3633650953857247774L;

	public ResourceExistsException(String message) {
		super(message);
	}

}

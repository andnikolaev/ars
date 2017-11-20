package com.epam.andrey_nikolaev.servlets_11.controller.exception;

public class NeedRegistrationException extends FailedChatOperationException {

	private static final long serialVersionUID = -1506756844789249332L;

	public NeedRegistrationException(String message) {
		super(message);
	}

}

package com.epam.andrey_nikolaev.servlets_11.controller.exception;

public class DontKickedException extends FailedChatOperationException {

	private static final long serialVersionUID = -1506756844789249332L;

	public DontKickedException(String message) {
		super(message);
	}

}

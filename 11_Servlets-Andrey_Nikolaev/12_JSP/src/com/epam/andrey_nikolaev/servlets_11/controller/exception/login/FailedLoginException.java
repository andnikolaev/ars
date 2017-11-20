package com.epam.andrey_nikolaev.servlets_11.controller.exception.login;

import com.epam.andrey_nikolaev.servlets_11.controller.exception.FailedChatOperationException;

public class FailedLoginException extends FailedChatOperationException {

	private static final long serialVersionUID = 8905883759433373708L;

	public FailedLoginException(String message) {
		super(message);
	}

}

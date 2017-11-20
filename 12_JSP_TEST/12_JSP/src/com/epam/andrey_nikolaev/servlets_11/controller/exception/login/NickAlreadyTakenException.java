package com.epam.andrey_nikolaev.servlets_11.controller.exception.login;

public class NickAlreadyTakenException extends FailedLoginException {

	private static final long serialVersionUID = -1506756844789249332L;

	public NickAlreadyTakenException(String message) {
		super(message);
	}

}

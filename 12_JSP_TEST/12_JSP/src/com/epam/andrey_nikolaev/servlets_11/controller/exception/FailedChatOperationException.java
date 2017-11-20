package com.epam.andrey_nikolaev.servlets_11.controller.exception;

public class FailedChatOperationException extends RuntimeException {

	private static final long serialVersionUID = 7044571488312611950L;

	public FailedChatOperationException(String message) {
		super(message);
	}
}

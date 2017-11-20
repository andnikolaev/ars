package com.epam.andrey_nikolaev.servlets_11.controller.helpers;

import java.security.InvalidParameterException;

public class IllegalCommandException extends InvalidParameterException {
	private static final long serialVersionUID = -2266020226971999379L;

	public IllegalCommandException() {
		super("Query has't command!");
	}

	public IllegalCommandException(String msg) {
		super(msg);
	}
}

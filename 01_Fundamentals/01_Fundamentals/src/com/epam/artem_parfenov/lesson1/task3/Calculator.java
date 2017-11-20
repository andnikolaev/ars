package com.epam.artem_parfenov.lesson1.task3;

import java.util.Scanner;

public class Calculator {

	private final static String NUMBER_A_MESSAGE = "\nEnter the integer number A:";
	private final static String NUMBER_B_MESSAGE = "Enter the integer number B:";
	private final static String NEXT_LINE = "\n";
	private final static String ENTER_MESSAGE = "Enter ";
	private final static String CALCULATE_MESSAGE = " to calculate ";
	private final static String NUMBER_A = "A";
	private final static String NUMBER_B = "B";
	private final static String PLUS = "+";
	private final static String MINUS = "-";
	private final static String ASTERISK = "*";
	private final static String SLASH = "/";
	private final static String EQUALLY = "=";
	private final static String INCORRECT_OPERATION_MESSAGE = "Incorrect operation!";
	private final static String CASES_STRING_FORMAT = "%d%s%d%s%d";

	private int numberA;
	private int numberB;
	private String operationMark;

	public String calculate(Scanner in) {

		this.numberA = setNumber(in, NUMBER_A_MESSAGE);
		this.numberB = setNumber(in, NUMBER_B_MESSAGE);
		this.operationMark = setOperationMark(in);

		return getOperationResult(this.numberA, this.numberB, this.operationMark);
	}

	private static int setNumber(Scanner in, String message) {

		System.out.println(message);
		int number = in.nextInt();

		return number;
	}

	private static String setOperationMark(Scanner in) {

		System.out.println(formUserMenu());
		in.nextLine();
		String operationMark = in.nextLine();

		return operationMark;
	}

	private static StringBuilder formUserMenu() {

		StringBuilder userMenu = new StringBuilder();

		userMenu.append(ENTER_MESSAGE).append(PLUS).append(CALCULATE_MESSAGE).append(NUMBER_A).append(PLUS)
				.append(NUMBER_B).append(NEXT_LINE).append(ENTER_MESSAGE).append(MINUS).append(CALCULATE_MESSAGE)
				.append(NUMBER_A).append(MINUS).append(NUMBER_B).append(NEXT_LINE).append(ENTER_MESSAGE)
				.append(ASTERISK).append(CALCULATE_MESSAGE).append(NUMBER_A).append(ASTERISK).append(NUMBER_B)
				.append(NEXT_LINE).append(ENTER_MESSAGE).append(SLASH).append(CALCULATE_MESSAGE).append(NUMBER_A)
				.append(SLASH).append(NUMBER_B);

		return userMenu;
	}

	private static String getOperationResult(int numberA, int numberB, String operationMark) {

		String operationResult = new String();

		switch (operationMark.trim()) {

		case PLUS:
			operationResult = String.format(CASES_STRING_FORMAT, numberA, PLUS, numberB, EQUALLY, (numberA + numberB));
			break;
		case MINUS:
			operationResult = String.format(CASES_STRING_FORMAT, numberA, MINUS, numberB, EQUALLY, (numberA - numberB));
			break;
		case ASTERISK:
			operationResult = String.format(CASES_STRING_FORMAT, numberA, ASTERISK, numberB, EQUALLY,
					(numberA * numberB));
			break;
		case SLASH:
			operationResult = String.format(CASES_STRING_FORMAT, numberA, SLASH, numberB, EQUALLY, (numberA / numberB));
			break;
		default:
			operationResult = INCORRECT_OPERATION_MESSAGE;
			break;
		}

		return operationResult;
	}
}

package com.epam.artem_parfenov.lesson1;

import java.util.Scanner;

import com.epam.artem_parfenov.lesson1.task1.NumberArray;
import com.epam.artem_parfenov.lesson1.task2.StringArray;
import com.epam.artem_parfenov.lesson1.task3.Calculator;

public class Runner {

	final static int ARRAY_ELEMENTS_AMOUNT = 20;
	final static int MAX_ARRAY_ELEMENT = 10;
	final static int MIN_ARRAY_ELEMENT = -10;

	final static String ARRAY_MESSAGE = "Initial array:";
	final static String ELEMENTS_EVEN_POSITION_SUM_MESSAGE = "Sum of array elements in even positions:";
	final static String STRINGS_AVERAGE_LENGTH_MESSAGE = "Average length of the string array:";
	final static String STRINGS_LONGER_AVERAGE_LENGTH_MESSAGE = "Strings that are longer than the average length:";

	public static void main(String[] args) {

		Runner runner = new Runner();
		runner.startRunner();
	}

	public void startRunner() {

		NumberArray numbers = new NumberArray();
		numbers.setArray(ARRAY_ELEMENTS_AMOUNT, MAX_ARRAY_ELEMENT, MIN_ARRAY_ELEMENT);

		System.out.println(ARRAY_MESSAGE);
		System.out.println(numbers.getStringViewArray());
		System.out.println(ELEMENTS_EVEN_POSITION_SUM_MESSAGE);
		System.out.println(numbers.findArrayElementsEvenPositionSum());

		Scanner in = new Scanner(System.in);
		StringArray strings = new StringArray();
		strings.setStringArray(in);
		int stringAverageLength = strings.findStringAverageLength();

		System.out.println(STRINGS_AVERAGE_LENGTH_MESSAGE);
		System.out.println(stringAverageLength);
		System.out.println(STRINGS_LONGER_AVERAGE_LENGTH_MESSAGE);
		System.out.println(strings.findStringsLongerAverageLength(stringAverageLength));

		Calculator calculator = new Calculator();

		System.out.println(calculator.calculate(in));
	}
}

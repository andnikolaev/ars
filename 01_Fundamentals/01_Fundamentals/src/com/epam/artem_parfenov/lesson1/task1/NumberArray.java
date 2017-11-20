package com.epam.artem_parfenov.lesson1.task1;

public class NumberArray {

	private final static String OPENING_SQUARE_BRACKET = "[";
	private final static String CLOSING_SQUARE_BRACKET = "]";
	private final static String COMMA = ",";
	private final static String SPACE = " ";
	private final static String FIRST_CASE_STRING_FORMAT = "%d%s%s";
	private final static String SECOND_CASE_STRING_FORMAT = "%d";

	private int[] numbers;

	public void setArray(int arrayElementsAmount, int maxArrayElement, int minArrayElement) {

		numbers = new int[arrayElementsAmount];

		for (int i = 0; i < numbers.length; i++) {

			numbers[i] = generateArrayElement(maxArrayElement, minArrayElement);
		}
	}

	private static int generateArrayElement(int maxArrayElement, int minArrayElement) {

		return (int) (Math.random() * (++maxArrayElement - minArrayElement)) + minArrayElement;
	}

	public int findArrayElementsEvenPositionSum() {

		int arrayElementsEvenPositionSum = 0;

		for (int i = 1; i < this.numbers.length; i += 2) {

			arrayElementsEvenPositionSum += this.numbers[i];
		}

		return arrayElementsEvenPositionSum;
	}

	public StringBuilder getStringViewArray() {

		StringBuilder stringViewArray = new StringBuilder();
		stringViewArray.append(OPENING_SQUARE_BRACKET);

		for (int i = 0; i < this.numbers.length; i++) {

			stringViewArray.append(getStringArrayElement(this.numbers, i));
		}

		stringViewArray.append(CLOSING_SQUARE_BRACKET);
		return stringViewArray;
	}

	private static String getStringArrayElement(int[] array, int elementIndex) {

		if (elementIndex != (array.length - 1)) {

			return String.format(FIRST_CASE_STRING_FORMAT, array[elementIndex], COMMA, SPACE);
		} else {

			return String.format(SECOND_CASE_STRING_FORMAT, array[elementIndex]);
		}
	}
}

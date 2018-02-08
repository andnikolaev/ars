package ru.rsreu.nikolaev0309;

import java.util.Arrays;

public class ElementsWithoutRepetitionsFinder {

	private int[] arrayBody;

	public ElementsWithoutRepetitionsFinder(int... args) {
		this.arrayBody = new int[args.length];
		setArrayElements(args);
	}

	@Override
	public String toString() {
		return Arrays.toString(arrayBody);
	}

	public String getArrayElementsWithoutRepetitions(String delimiter) {
		int[] numbers = this.arrayBody;

		StringBuilder result = new StringBuilder();
		result.append(numbers[0]).append(delimiter);

		for (int i = 1; i < numbers.length; i++) {
			if (!isArrayContainElement(numbers, 0, i - 1, numbers[i])) {
				result.append(numbers[i]).append(delimiter);
			}
		}

		return result.toString();
	}

	private boolean isArrayContainElement(int[] numbers, int fromIndex,
			int toIndex, int element) {

		for (int i = fromIndex; i <= toIndex; i++) {
			if (numbers[i] == element) {
				return true;
			}
		}
		return false;
	}

	private void setArrayElements(int... args) {
		int length = args.length;
		for (int i = 0; i < length; i++) {
			this.arrayBody[i] = args[i];
		}
	}
}

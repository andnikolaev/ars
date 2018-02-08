package ru.rsreu.nikolaev0309;

import java.util.Locale;

public class Runner {

	private Runner() {
	}

	public static void main(String[] args) {
		StringBuilder result;
		Locale.setDefault(Locale.US);
		result = new StringBuilder();

		ElementsWithoutRepetitionsFinder arrayObject = new ElementsWithoutRepetitionsFinder(
				1, 2, 10, 3, 1, -2, 10, 5, 5);
		result.append(Resourcer.getString("message.source"))
				.append(arrayObject).append("\n");

		String elementsWithoutRepetition = arrayObject
				.getArrayElementsWithoutRepetitions(Resourcer
						.getString("message.delimiter"));
		result.append(Resourcer.getString("message.worked")).append(
				elementsWithoutRepetition);

		System.out.println(result);
	}
}

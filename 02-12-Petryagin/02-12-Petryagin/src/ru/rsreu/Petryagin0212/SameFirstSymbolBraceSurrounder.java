package ru.rsreu.Petryagin0212;

public class SameFirstSymbolBraceSurrounder {

	private SameFirstSymbolBraceSurrounder() {
	}

	public static String surroundByBraces(String inputString) {
		String string = "";
		String[] words = inputString.split(" ");

		if (words.length < 2) {
			return null;
		}

		char symbol = words[words.length - 2].charAt(0);

		for (int i = 0; i < words.length; i++) {
			if (words[i].charAt(0) == symbol) {

				string += String.format("(%s)", words[i]);
			} else {
				string += words[i];
			}
			string += " ";
		}

		return string;
	}

}

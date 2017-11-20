package com.epam.artem_parfenov.lesson1.task2;

import java.util.Scanner;

public class StringArray {

	private final static String STRING_ARRAY_SIZE_MESSAGE = "\nEnter the size of the string array:";
	private final static String STRING_ARRAY_MESSAGE = "Enter the string array:";
	private final static String STRING_NUMBER_MESSAGE = " string of the array: ";
	private final static String POINT = ". ";
	private final static String STRING_LENGTH_MESSAGE = "Length of this string: ";
	private final static String NEXT_LINE = "\n";

	private String[] strings;

	public void setStringArray(Scanner in) {

		this.strings = new String[setStringArraySize(in)];
		System.out.println(STRING_ARRAY_MESSAGE);
		in.nextLine();

		for (int i = 0; i < this.strings.length; i++) {

			this.strings[i] = in.nextLine();
		}
	}

	private static int setStringArraySize(Scanner in) {

		System.out.println(STRING_ARRAY_SIZE_MESSAGE);
		int size = in.nextInt();

		return size;
	}

	public int findStringAverageLength() {

		return getArrayStringsLengthSum(this.strings) / this.strings.length;
	}

	private static int getArrayStringsLengthSum(String[] stringArray) {

		int stringAverageLength = 0;

		for (String string : stringArray) {

			stringAverageLength += string.length();
		}

		return stringAverageLength;
	}

	public StringBuilder findStringsLongerAverageLength(int stringAverageLength) {

		StringBuilder stringsLongerAverageLength = new StringBuilder();

		for (int i = 0; i < this.strings.length; i++) {

			if (this.strings[i].length() > stringAverageLength) {

				stringsLongerAverageLength.append(i + 1).append(STRING_NUMBER_MESSAGE).append(this.strings[i])
						.append(POINT).append(STRING_LENGTH_MESSAGE).append(this.strings[i].length()).append(POINT)
						.append(NEXT_LINE);
			}
		}

		return stringsLongerAverageLength;
	}
}

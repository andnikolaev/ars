package ru.rsreu.Petryagin0212;

import java.util.Scanner;

public class Runner {

	private Runner() {
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		System.out.println("Enter number of strings: n = ");
		int stringCount = Integer.parseInt(in.next());
		in.nextLine();

		String[] stringArray = new String[stringCount];
		for (int i = 0; i < stringArray.length; i++) {
			System.out.println("Enter " + (i + 1) + ". string: ");
			stringArray[i] = in.nextLine();
		}

		in.close();

		for (int i = 0; i < stringArray.length; i++) {
			System.out.println("Enter " + (i + 1) + ". string: ");

			String stringOriginal = stringArray[i].trim();

			if (stringOriginal.isEmpty()) {
				System.out.println("String is empty!");
			} else {
				String string = SameFirstSymbolBraceSurrounder
						.surroundByBraces(stringOriginal);

				if (string == null) {
					System.out
							.println("String should contain at least TWO words separated by space symbol!");
				} else {
					System.out.println(string);
				}
			}
		}
	}

}

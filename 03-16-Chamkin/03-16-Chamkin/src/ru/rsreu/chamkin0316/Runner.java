package ru.rsreu.chamkin0316;

import java.util.Locale;

import com.prutzkow.resourcebundledemo.Resourcer;

public class Runner {
	public static final int INDEX = 4;

	private Runner() {
	}

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);

		ArrayElementsRemoverByIndexEvenLeftOddRightShifter array = new ArrayElementsRemoverByIndexEvenLeftOddRightShifter(
				1, 2, 3, 4, 5, 6);

		StringBuilder sb = new StringBuilder();
		sb.append(Resourcer.getString("message.array.original"));
		sb.append(array);
		sb.append('\n');

		try {
			array.removeElement(INDEX);
		} catch (ArrayIndexOutOfBoundsException index) {
			System.out.println("Incorrect value of INDEX");
		}

		sb.append(Resourcer.getString("message.array.modified"));
		sb.append(array);
		System.out.println(sb);
	}

}

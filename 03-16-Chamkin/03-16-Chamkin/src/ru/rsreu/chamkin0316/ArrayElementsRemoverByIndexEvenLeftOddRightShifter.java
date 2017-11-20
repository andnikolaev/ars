package ru.rsreu.chamkin0316;

import java.util.Arrays;

public class ArrayElementsRemoverByIndexEvenLeftOddRightShifter {

	private int[] array;

	public ArrayElementsRemoverByIndexEvenLeftOddRightShifter(int... args) {
		array = new int[args.length];
		setElements(0, args);
	}

	private void setElements(int from, int... args) {
		for (int i = 0; i < args.length; ++i) {
			array[from + i] = args[i];
		}
	}

	private boolean isEven(int value) {
		return value % 2 == 0;
	}

	public void removeElement(int index) {
		if ((index == 0) || (Math.abs(index) > array.length)) {
			throw new ArrayIndexOutOfBoundsException(index);
		}
		if (index < 0) {
			index = array.length - Math.abs(index) + 1;
		}
		if (isEven(index)) {
			shiftPastIndexLeft(index - 1);
		} else {
			shiftPreIndexRight(index - 1);
		}
	}

	private void shiftPastIndexLeft(int index) {
		int[] elementsBefore = Arrays.copyOfRange(array, 0, index);
		int[] elementsAfter = Arrays
				.copyOfRange(array, index + 1, array.length);

		setElements(0, elementsBefore);
		setElements(index, elementsAfter);
		array[array.length - 1] = 0;
	}

	private void shiftPreIndexRight(int index) {
		int[] elementsBefore = Arrays.copyOfRange(array, 0, index);
		int[] elementsAfter = Arrays
				.copyOfRange(array, index + 1, array.length);

		array[0] = 0;
		setElements(1, elementsBefore);
		setElements(index + 1, elementsAfter);
	}

	@Override
	public String toString() {
		return Arrays.toString(array);
	}

}

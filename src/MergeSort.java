import java.util.Arrays;

public class MergeSort {

	public void sort(int[] array) {
		mergeSort(array, 0, array.length - 1);
	}

	void mergeSort(int[] array, int from, int to) {
		if (from < to) {
			int middle = from + ((to - from) / 2);
			mergeSort(array, from, middle);
			mergeSort(array, middle + 1, to);
			merge(array, from, middle, to);
		}
	}

	void merge(int[] array, int from, int middle, int to) {
		int[] left = Arrays.copyOfRange(array, from, middle + 1);
		int[] right = Arrays.copyOfRange(array, middle + 1, to + 1);

		int leftIndex = 0,
				rightIndex = 0;

		for (int i = from; i < to+1; i++) {

			if (leftIndex < left.length && rightIndex < right.length) {
				if (left[leftIndex] < right[rightIndex]) {
					array[i] = left[leftIndex];
					leftIndex++;
				} else {
					array[i] = right[rightIndex];
					rightIndex++;
				}
			} else if (leftIndex < left.length) {
				array[i] = left[leftIndex];
				leftIndex++;
			} else if (rightIndex < right.length) {
				array[i] = right[rightIndex];
				rightIndex++;
			}
		}
	}
}
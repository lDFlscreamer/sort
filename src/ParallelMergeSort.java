import java.util.Arrays;
import java.util.concurrent.RecursiveAction;

public class ParallelMergeSort extends RecursiveAction {

	private static final int SORT_THRESHOLD = 128;

	private final int[] array;
	private final int from;
	private final int to;

	public ParallelMergeSort(int[] array, int from, int to) {
		this.array = array;
		this.from = from;
		this.to = to;
	}


	@Override
	public void compute() {
		if (from < to) {
			int size = to - from;
			if (SORT_THRESHOLD < 2) {
				insertionSort();
			} else {
				int mid = from + Math.floorDiv(size, 2);
				invokeAll(
						new ParallelMergeSort(array, from, mid),
						new ParallelMergeSort(array, mid + 1, to));
				merge(mid);
			}
		}
	}

	public void insertionSort() {
		for (int i = from + 1; i <= to; ++i) {
			int current = array[i];
			int j = i - 1;
			while (from <= j && current < array[j]) {
				array[j + 1] = array[j];
				j--;
			}
			array[j + 1] = current;
		}
	}

	public void merge(int mid) {
		int[] left = Arrays.copyOfRange(array, from, mid + 1);
		int[] right = Arrays.copyOfRange(array, mid + 1, to + 1);
		int f = from;

		int leftIndex = 0,
				rightIndex = 0;

		for (int i = from; i < to; i++) {

			if (leftIndex < left.length && rightIndex < right.length) {
				if (left[leftIndex] <= right[rightIndex]) {
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
import java.util.Arrays;
import java.util.concurrent.RecursiveAction;

public class ParallelMergeSort extends RecursiveAction {

	private static final int SORT_THRESHOLD = 128;

	private final int[] values;
	private final int from;
	private final int to;

	public ParallelMergeSort(int[] values) {
		this(values, 0, values.length - 1);
	}

	public ParallelMergeSort(int[] values, int from, int to) {
		this.values = values;
		this.from = from;
		this.to = to;
	}


	@Override
	protected void compute() {
		if (from < to) {
			int size = to - from;
			if (size < SORT_THRESHOLD) {
				insertionSort();
			} else {
				int mid = from + Math.floorDiv(size, 2);
				invokeAll(
						new ParallelMergeSort(values, from, mid),
						new ParallelMergeSort(values, mid + 1, to));
				merge(mid);
			}
		}
	}

	private void insertionSort() {
		for (int i = from + 1; i <= to; ++i) {
			int current = values[i];
			int j = i - 1;
			while (from <= j && current < values[j]) {
				values[j + 1] = values[j--];
			}
			values[j + 1] = current;
		}
	}

	private void merge(int mid) {
		int[] left = Arrays.copyOfRange(values, from, mid + 1);
		int[] right = Arrays.copyOfRange(values, mid + 1, to + 1);
		int f = from;

		int leftIndex = 0,
				rightIndex = 0;

		for (int i = from; i < to; i++) {

			if (leftIndex < left.length && rightIndex < right.length) {
				if (left[leftIndex] <= right[rightIndex]) {
					values[i] = left[leftIndex];
					leftIndex++;
				} else {
					values[i] = right[rightIndex];
					rightIndex++;
				}
			} else if (leftIndex < left.length) {
				values[i] = left[leftIndex];
				leftIndex++;
			} else if (rightIndex < right.length) {
				values[i] = right[rightIndex];
				rightIndex++;
			}
		}
	}
}
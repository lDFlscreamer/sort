import java.util.Arrays;
import java.util.concurrent.RecursiveAction;

public class ParallelMergeSort extends RecursiveAction {

	private static final int SORT_THRESHOLD = 128;

	private final int[] array;
	private final int begin;
	private final int end;

	public ParallelMergeSort(int[] array, int begin, int end) {
		this.array = array;
		this.begin = begin;
		this.end = end;
	}

	public void insertionSort() {
		for (int i = begin + 1; i <= end; ++i) {
			int current = array[i];
			int j = i - 1;
			while (begin <= j && current < array[j]) {
				array[j + 1] = array[j];
				j--;
			}
			array[j + 1] = current;
		}
	}

	@Override
	public void compute() {
		if (begin < end) {
			int size = end - begin;
			if (size < SORT_THRESHOLD) {
				insertionSort();
			} else {
				int middle = begin + Math.floorDiv(size, 2);
				new ParallelMergeSort(array, begin, middle).invoke();
				new ParallelMergeSort(array, middle + 1, end).invoke();

				int[] leftPart = Arrays.copyOfRange(array, begin, middle + 1);
				int[] rightPart = Arrays.copyOfRange(array, middle + 1, end + 1);

				int leftIndex = 0;
				int rightIndex = 0;

				for (int i = begin; i < end; i++) {

					if (leftIndex < leftPart.length && rightIndex < rightPart.length) {
						if (leftPart[leftIndex] <= rightPart[rightIndex]) {
							array[i] = leftPart[leftIndex];
							leftIndex++;
						} else {
							array[i] = rightPart[rightIndex];
							rightIndex++;
						}
					} else if (leftIndex < leftPart.length) {
						array[i] = leftPart[leftIndex];
						leftIndex++;
					} else if (rightIndex < rightPart.length) {
						array[i] = rightPart[rightIndex];
						rightIndex++;
					}
				}
			}
		}
	}
}
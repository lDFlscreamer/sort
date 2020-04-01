import java.util.Arrays;
import java.util.concurrent.RecursiveAction;

public class ParallelMergeSort extends RecursiveAction {

	private static final int maximumForSerialSort = 2;

	private final int[] array;
	private final int begin;
	private final int end;

	public ParallelMergeSort(int[] array, int begin, int end) {
		this.array = array;
		this.begin = begin;
		this.end = end;
	}

	@Override
	public void compute() {
		if (begin < end) {
			int size = end - begin;
			if (size < maximumForSerialSort) {
				MergeSort.mergeSort(array,begin,end);
			} else {
				int middle = begin + Math.floorDiv(size, 2);
				new ParallelMergeSort(array, begin, middle).invoke();
				new ParallelMergeSort(array, middle + 1, end).invoke();

				int[] leftPart = Arrays.copyOfRange(array, begin, middle + 1);
				int[] rightPart = Arrays.copyOfRange(array, middle + 1, end + 1);
				int resultIndex = begin;
				int leftIndex = 0;
				int rightIndex = 0;

				while (leftIndex < leftPart.length && rightIndex < rightPart.length) {
					if (leftPart[leftIndex] <= rightPart[rightIndex]) {
						array[resultIndex++] = leftPart[leftIndex++];
					} else {
						array[resultIndex++] = rightPart[rightIndex++];
					}
				}
				while (leftIndex < leftPart.length) {
					array[resultIndex++] = leftPart[leftIndex++];
				}
				while (rightIndex < rightPart.length) {
					array[resultIndex++] = rightPart[rightIndex++];
				}

			}
		}

	}

}
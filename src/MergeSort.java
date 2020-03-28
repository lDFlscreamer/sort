import java.util.Arrays;

public class MergeSort {

	public static void mergeSort(int[] array, int begin, int end) {
		if (begin < end) {
			int middle = begin + ((end - begin) / 2);
			mergeSort(array, begin, middle);
			mergeSort(array, middle + 1, end);
			int[] leftPart = Arrays.copyOfRange(array, begin, middle + 1);
			int[] rightPart = Arrays.copyOfRange(array, middle + 1, end + 1);

			int leftIndex = 0;
			int rightIndex = 0;

			for (int i = begin; i < end + 1; i++) {

				if (leftIndex < leftPart.length && rightIndex < rightPart.length) {
					if (leftPart[leftIndex] < rightPart[rightIndex]) {
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
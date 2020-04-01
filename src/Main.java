import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;

public class Main {
	static int size = 20;

	static long serial(int size) {
		int[] arr = Generator.generate(size);
		System.out.println("Array = " + Arrays.toString(arr));
		long start = System.nanoTime();
		MergeSort.mergeSort(arr,0,arr.length-1);
		long end = System.nanoTime();
		System.out.println("Array after mergesort =\t\t " + Arrays.toString(arr));
		return end - start;
	}
	static long serial(int[] arr) {
		long start = System.nanoTime();
		MergeSort.mergeSort(arr,0,arr.length-1);
		long end = System.nanoTime();
		System.out.println("Array after mergesort =\t\t " + Arrays.toString(arr));
		return end - start;
	}

	static long parallel(int size) {
		int[] arr = Generator.generate(size);
		System.out.println("Array = " + Arrays.toString(arr));
		ForkJoinPool forkJoinPool = new ForkJoinPool();

		long start = System.nanoTime();
		forkJoinPool.invoke(new ParallelMergeSort(arr,0,arr.length-1));
		long end = System.nanoTime();
		System.out.println("Array after parallel mergesort =\t\t " + Arrays.toString(arr));
		return end - start;
	}
	static long parallel(int[] arr) {
		ForkJoinPool forkJoinPool = new ForkJoinPool();

		long start = System.nanoTime();
		forkJoinPool.invoke(new ParallelMergeSort(arr,0,arr.length-1));
		long end = System.nanoTime();
		System.out.println("Array after parallel mergesort =\t\t " + Arrays.toString(arr));
		return end - start;
	}

	public static void main(String[] args) {
		int[] arr = Generator.generate(size);
		int[] arr2 = Arrays.copyOf(arr,arr.length);
		System.out.println("Array = " + Arrays.toString(arr));
		long serial = serial(arr);
		long parallel = parallel(arr2);
		System.out.println("serial  time : \t\t\t\t" + serial);
		System.out.println("parallel time: \t\t\t\t" + parallel);
	}


}

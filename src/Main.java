import java.util.concurrent.ForkJoinPool;

public class Main {
	static int size = 1_000_000;

	static long serial(int size) {
		int[] generate = Generator.generate(size);
//		System.out.println("generate = " + Arrays.toString(generate));
		MergeSort mergeSort = new MergeSort();
		long start = System.nanoTime();
		mergeSort.mergeSort(generate,0,generate.length-1);
		long end = System.nanoTime();
//		System.out.println("generate = " + Arrays.toString(generate));
		return end - start;
	}

	static long parallel(int size) {
		int[] generate = Generator.generate(size);
//		System.out.println("generate = " + Arrays.toString(generate));
		ForkJoinPool forkJoinPool = new ForkJoinPool();

		long start = System.nanoTime();
		forkJoinPool.invoke(new ParallelMergeSort(generate,0,generate.length-1));
		long end = System.nanoTime();
//		System.out.println("generate = " + Arrays.toString(generate));
		return end - start;
	}

	public static void main(String[] args) {
		long serial = serial(size);
		long parallel = parallel(size);
		System.out.println("serial  time : \t\t\t\t" + serial);
		System.out.println("parallel time: \t\t\t\t" + parallel);
	}


}

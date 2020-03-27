import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ForkJoinPool;

public class Main {
	public static void main(String[] args) {
		int size=1_000_000;
		long sequensial = sequensial(size);
		long parallel = parallel(size);
		System.out.println("seq: \t\t\t\t"+ sequensial);
		System.out.println("parallel: \t\t\t"+ parallel);
	}

	static long sequensial(int size) {
		int[] generate=Generator.generate(size);
//		System.out.println("generate = " + Arrays.toString(generate));
		MergeSort mergeSort=new MergeSort();
		long start = System.nanoTime();
		mergeSort.sort(generate);
		long end = System.nanoTime();
//		System.out.println("generate = " + Arrays.toString(generate));
		return end - start;
	}

	static long parallel(int size) {
		int[] generate = Generator.generate(size);
//		System.out.println("generate = " + Arrays.toString(generate));
		ForkJoinPool forkJoinPool = new ForkJoinPool();

		long start = System.nanoTime();
		forkJoinPool.invoke(new ParallelMergeSort(generate));
		long end = System.nanoTime();
//		System.out.println("generate = " + Arrays.toString(generate));
		return end - start;
	}
}

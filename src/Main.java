import java.util.List;

public class Main {
	public static void main(String[] args) {
		List<Integer> generate = Generator.generate(100);
		System.out.println("generate = " + generate);
		MergeSort mergeSort=new MergeSort();
		generate=mergeSort.sort(generate);
		System.out.println("generate = " + generate);
	}
}

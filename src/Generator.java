import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Generator {

	public static List<Integer> generate(int size) {
		ArrayList<Integer> numbers = new ArrayList<>();
		for (int i = 0; i < size; i++) {
			numbers.add(ThreadLocalRandom.current().nextInt(10));
		}
		return numbers;
	}
}

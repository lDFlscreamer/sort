import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Generator {

	public static int[] generate(int size) {
		int[] numbers = new int[size];
		for (int i = 0; i < size; i++) {
			numbers[i]=ThreadLocalRandom.current().nextInt(10);
		}
		return numbers;
	}
}

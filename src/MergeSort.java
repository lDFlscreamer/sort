import java.util.ArrayList;
import java.util.List;

public class MergeSort {

	public List<Integer> sort(List<Integer> toSort){
		if(toSort.size()<=1){
			return toSort;
		}
		ArrayList<Integer> result = new ArrayList<>();
		int subListSize = toSort.size() / 2;
		List<Integer> leftList = sort(toSort.subList(0, subListSize));
		List<Integer> rightList = sort(toSort.subList(subListSize, toSort.size()));
		int leftSize = leftList.size();
		int rightSize = rightList.size();
		int i=0;
		int j=0;

		for (int k = 0; k < leftSize + rightSize; k++) {
			if (i < leftSize && j < rightSize) {
				if (leftList.get(i) < rightList.get(j)) {
					result.add(leftList.get(i));
					i++;
				} else {
					result.add(rightList.get(j));
					j++;
				}
			} else if (i < leftSize) {
				result.add(leftList.get(i));
				i++;
			} else if (j < rightSize) {
				result.add(rightList.get(j));
				j++;
			}
		}

		return result;

	}

}

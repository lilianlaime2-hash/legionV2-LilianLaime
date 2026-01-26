package sort;

import model.Character;
import java.util.List;

public class InsertionSort implements SortStrategy {

    @Override
    public void sort(List<Character> troops) {
        for (int i = 1; i < troops.size(); i++) {
            Character key = troops.get(i);
            int j = i - 1;

            while (j >= 0 && troops.get(j).getRank() > key.getRank()) {
                troops.set(j + 1, troops.get(j));
                j--;
            }
            troops.set(j + 1, key);
        }
    }
}

package sort;

import model.Character;
import java.util.List;

public class QuickSort implements SortStrategy {

    @Override
    public void sort(List<Character> troops) {
        troops.sort((a, b) -> Integer.compare(a.getRank(), b.getRank()));
    }
}

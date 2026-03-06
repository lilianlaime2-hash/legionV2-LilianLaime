package sorting.algorithms;

import creation.model.character.Character;
import sorting.ConsoleSortObserver;
import sorting.SortStrategy;

import java.util.Comparator;
import java.util.List;

/**
 * Reference source: https://www.geeksforgeeks.org/insertion-sort-algorithm/
 * Implementation adapted for this project (Character types, comparator, and observer).
 */
public class InsertionSort implements SortStrategy {


    @Override
    public void sort (List<Character> list, Comparator<Character> comparator, ConsoleSortObserver observer) {
        int n = list.size();

        for (int i = 1; i < n; i++){
            Character key = list.get(i);
            int j = i - 1;

            while (j >= 0) {
                int cmp = comparator.compare(list.get(j), key);
                if (cmp <= 0) break;

                list.set(j+1, list.get(j));
                j--;

            }

            int targetIndex = j + 1;
            if (targetIndex != i) {
                list.set(targetIndex, key);
                observer.notifyStep(list);
            }
        }
    }
}

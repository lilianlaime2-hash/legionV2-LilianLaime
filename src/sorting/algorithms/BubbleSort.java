package sorting.algorithms;

import creation.model.character.Character;
import sorting.ConsoleSortObserver;
import sorting.SortStrategy;

import java.util.Comparator;
import java.util.List;

/**
 * Reference source: https://www.geeksforgeeks.org/bubble-sort-algorithm/
 * Implementation adapted for this project (Character types, comparator, and observer).
 */
public class BubbleSort implements SortStrategy {

    @Override
    public void sort(List<Character> list, Comparator<Character> comparator, ConsoleSortObserver observer) {
        int n = list.size();
        boolean swapped;

        for (int i = 0; i < n - 1; i++) {
            swapped = false;

            for (int j = 0; j < n - 1 - i; j++) {
                int cmp = comparator.compare(list.get(j), list.get(j + 1));

                if (cmp > 0) {
                    Character tmp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, tmp);
                    swapped = true;
                    observer.notifyStep(list);
                }
            }

            if (!swapped) break;
        }
    }
}

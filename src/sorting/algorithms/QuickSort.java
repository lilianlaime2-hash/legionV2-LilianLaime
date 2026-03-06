package sorting.algorithms;

import creation.model.character.Character;
import sorting.ConsoleSortObserver;
import sorting.SortStrategy;

import java.util.Comparator;
import java.util.List;

/**
 * Reference source: https://www.geeksforgeeks.org/dsa/iterative-quick-sort/
 * Implementation adapted for this project ((Character types, comparator, and observer).
 */
public class QuickSort implements SortStrategy {

    @Override
    public void sort(List<Character> list, Comparator<Character> comparator, ConsoleSortObserver observer) {
        quickSort(list, comparator, observer, 0, list.size() - 1);
    }

    private void quickSort(List<Character> list, Comparator<Character> comparator, ConsoleSortObserver observer,
                           int low, int high) {

        if (low >= high) return;

        int pivotIndex = partition(list, comparator, observer, low, high);
        quickSort(list, comparator, observer, low, pivotIndex - 1);
        quickSort(list, comparator, observer, pivotIndex + 1, high);
    }

    private int partition(List<Character> list, Comparator<Character> comparator, ConsoleSortObserver observer,
                          int low, int high) {

        Character pivot = list.get(high);
        int i = low;

        for (int j = low; j < high; j++) {
            int cmp = comparator.compare(list.get(j), pivot);

            if (cmp <= 0) {
                boolean changed = swap(list, i, j);
                i++;
                if (changed) {
                    observer.notifyStep(list);
                }
            }
        }
        boolean pivotMoved = swap(list, i, high);
        if (pivotMoved) {
            observer.notifyStep(list);
        }
        return i;
    }

    private boolean swap(List<Character> list, int i, int j) {
        if (i == j) return false;
        Character tmp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, tmp);
        return true;
    }
}

package sorting;

import model.Character;

import java.util.Comparator;
import java.util.List;

public class QuickSort<T extends Character> implements SortStrategy<T> {

    @Override
    public void sort(
            List<T> list,
            Comparator<T> comparator,
            ConsoleSortObserver<T> observer
    ) {
        quickSort(list, comparator, observer, 0, list.size() - 1);
    }

    private void quickSort(
            List<T> list,
            Comparator<T> comparator,
            ConsoleSortObserver<T> observer,
            int low,
            int high
    ) {
        if (low >= high) return;

        int p = partition(list, comparator, observer, low, high);
        quickSort(list, comparator, observer, low, p - 1);
        quickSort(list, comparator, observer, p + 1, high);
    }

    private int partition(
            List<T> list,
            Comparator<T> comparator,
            ConsoleSortObserver<T> observer,
            int low,
            int high
    ) {
        T pivot = list.get(high);
        int i = low;

        for (int j = low; j < high; j++) {
            int cmp = comparator.compare(list.get(j), pivot);

            if (cmp <= 0) {
                boolean changed = swap(list, i, j);
                i++;
                if (changed) {
                    observer.showStep(list);
                }
            }
        }
        boolean pivotMoved = swap(list, i, high);
        if (pivotMoved) {
            observer.showStep(list);
        }
        return i;
    }

    private boolean swap(List<T> list, int i, int j) {
        if (i == j) return false;
        T tmp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, tmp);
        return true;
    }
}

package sorting;

import java.util.Comparator;
import java.util.List;

public class QuickSort<T> implements SortStrategy<T> {

    @Override
    public void sort(List<T> list, Comparator<T> comparator) {
        quickSort(list, comparator, 0, list.size() - 1);
    }

    private void quickSort(List<T> list, Comparator<T> comparator, int low, int high) {
        if (low >= high) return;

        int p = partition(list, comparator, low, high);
        quickSort(list, comparator, low, p - 1);
        quickSort(list, comparator, p + 1, high);
    }

    private int partition(List<T> list, Comparator<T> comparator, int low, int high) {
        T pivot = list.get(high);
        int i = low;

        for (int j = low; j < high; j++) {
            if (comparator.compare(list.get(j), pivot) <= 0) {
                swap(list, i, j);
                i++;
            }
        }
        swap(list, i, high);
        return i;
    }

    private void swap(List<T> list, int i, int j) {
        T tmp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, tmp);
    }
}
package sort;

import java.util.Comparator;
import java.util.List;

public class QuickSort<T> implements SortStrategy<T> {

    @Override
    public void sort(List<T> list, Comparator<T> comparator) {
        quickSort(list, comparator, 0, list.size() - 1);
    }

    private void quickSort(List<T> list, Comparator<T> comp, int low, int high) {
        if (low < high) {
            int pi = partition(list, comp, low, high);
            quickSort(list, comp, low, pi - 1);
            quickSort(list, comp, pi + 1, high);
        }
    }

    private int partition(List<T> list, Comparator<T> comp, int low, int high) {

        T pivot = list.get(high);
        int i = low - 1;

        for (int j = low; j < high; j++) {

            if (comp.compare(list.get(j), pivot) <= 0) {
                i++;
                swap(list, i, j);
            }
        }

        swap(list, i + 1, high);
        return i + 1;
    }

    private void swap(List<T> list, int i, int j) {
        T temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }
}

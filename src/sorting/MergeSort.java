package sorting;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MergeSort<T> implements SortStrategy<T> {

    @Override
    public void sort(List<T> list, Comparator<T> comparator) {
        if (list.size() <= 1) return;

        mergeSort(list, comparator, 0, list.size() - 1);
    }

    private void mergeSort(List<T> list, Comparator<T> comp, int left, int right) {

        if (left < right) {
            int mid = (left + right) / 2;

            mergeSort(list, comp, left, mid);
            mergeSort(list, comp, mid + 1, right);

            merge(list, comp, left, mid, right);
        }
    }

    private void merge(List<T> list, Comparator<T> comp, int left, int mid, int right) {

        List<T> temp = new ArrayList<>();

        int i = left;
        int j = mid + 1;

        while (i <= mid && j <= right) {

            if (comp.compare(list.get(i), list.get(j)) <= 0) {
                temp.add(list.get(i++));
            } else {
                temp.add(list.get(j++));
            }
        }

        while (i <= mid) temp.add(list.get(i++));
        while (j <= right) temp.add(list.get(j++));

        for (int k = 0; k < temp.size(); k++) {
            list.set(left + k, temp.get(k));
        }
    }
}

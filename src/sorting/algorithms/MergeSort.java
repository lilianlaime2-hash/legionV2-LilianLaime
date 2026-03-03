package sorting.algorithms;

import creation.model.character.Character;
import sorting.ConsoleSortObserver;
import sorting.SortStrategy;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class MergeSort<T extends Character> implements SortStrategy<T> {

    @Override
    public void sort(
            List<T> list,
            Comparator<T> comparator,
            ConsoleSortObserver<T> observer
    ) {
        if (list.size() <= 1) return;
        List<T> sorted = mergeSort(new ArrayList<>(list), comparator);
        for (int i = 0; i < list.size(); i++) {
            T current = list.get(i);
            T target = sorted.get(i);
            if (!Objects.equals(current, target)) {
                list.set(i, target);
                observer.showStep(list);
            }
        }
    }

    private List<T> mergeSort(List<T> list, Comparator<T> comparator) {
        if (list.size() <= 1) return list;

        int mid = list.size() / 2;
        List<T> left = mergeSort(new ArrayList<>(list.subList(0, mid)), comparator);
        List<T> right = mergeSort(new ArrayList<>(list.subList(mid, list.size())), comparator);

        return merge(left, right, comparator);
    }

    private List<T> merge(List<T> left, List<T> right, Comparator<T> comparator) {
        List<T> result = new ArrayList<>();
        int i = 0, j = 0;

        while (i < left.size() && j < right.size()) {
            int cmp = comparator.compare(left.get(i), right.get(j));

            if (cmp <= 0) result.add(left.get(i++));
            else result.add(right.get(j++));
        }

        while (i < left.size()) {
            result.add(left.get(i++));
        }
        while (j < right.size()) {
            result.add(right.get(j++));
        }

        return result;
    }
}

package sorting.algorithms;

import creation.model.character.Character;
import sorting.ConsoleSortObserver;
import sorting.SortStrategy;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

/**
 * Reference source: https://www.geeksforgeeks.org/merge-sort/
 * Implementation adapted for this project ((Character types, comparator, and observer).
 */
public class MergeSort implements SortStrategy {

    @Override
    public void sort(List<Character> list, Comparator<Character> comparator, ConsoleSortObserver observer) {

        if (list.size() <= 1) return;

        List<Character> copy = new ArrayList<>(list);
        List<Character> sorted = mergeSort(copy, comparator);

        for (int i = 0; i < list.size(); i++) {
            Character current = list.get(i);
            Character target = sorted.get(i);
            if (!Objects.equals(current, target)) {
                list.set(i, target);
                observer.notifyStep(list);
            }
        }
    }

    private List<Character> mergeSort(List<Character> list, Comparator<Character> comparator) {
        if (list.size() <= 1) return list;

        int mid = list.size() / 2;
        List<Character> left = mergeSort(new ArrayList<>(list.subList(0, mid)), comparator);
        List<Character> right = mergeSort(new ArrayList<>(list.subList(mid, list.size())), comparator);

        return merge(left, right, comparator);
    }

    private List<Character> merge(List<Character> left, List<Character> right, Comparator<Character> comparator) {
        List<Character> result = new ArrayList<>();
        int i = 0, j = 0;

        while (i < left.size() && j < right.size()) {
            int cmp = comparator.compare(left.get(i), right.get(j));

            if (cmp <= 0) {
                result.add(left.get(i++));
            } else result.add(right.get(j++));
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

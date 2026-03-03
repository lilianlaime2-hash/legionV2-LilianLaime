package sorting.algorithms;

import creation.model.character.Character;
import sorting.ConsoleSortObserver;
import sorting.SortStrategy;

import java.util.Comparator;
import java.util.List;

public class BubbleSort<T extends Character> implements SortStrategy<T> {

    @Override
    public void sort(
            List<T> list,
            Comparator<T> comparator,
            ConsoleSortObserver<T> observer
    ) {
        int n = list.size();
        boolean swapped;

        for (int i = 0; i < n - 1; i++) {
            swapped = false;

            for (int j = 0; j < n - 1 - i; j++) {
                int cmp = comparator.compare(list.get(j), list.get(j + 1));

                if (cmp > 0) {
                    T tmp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, tmp);
                    swapped = true;
                    observer.showStep(list);
                }
            }

            if (!swapped) break;
        }
    }
}

package sorting;

import model.Character;

import java.util.Comparator;
import java.util.List;

public class InsertionSort<T extends Character> implements SortStrategy<T> {


    @Override
    public void sort (
            List<T> list,
            Comparator<T> comparator,
            ConsoleSortObserver<T> observer
    ) {
        int n = list.size();

        for (int i = 1; i < n; i++){
            T key = list.get(i);
            int j = i - 1;

            while (j >= 0) {
                int cmp = comparator.compare(list.get(j), key);
                if (cmp <= 0) break;

                list.set(j+1, list.get(j));
                j--;
                observer.showStep(list);
            }

            int targetIndex = j + 1;
            if (targetIndex != i) {
                list.set(targetIndex, key);
                observer.showStep(list);
            }
        }
    }
}

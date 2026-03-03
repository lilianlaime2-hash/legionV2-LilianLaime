package sorting;

import creation.model.character.Character;
import sorting.algorithms.BubbleSort;
import sorting.algorithms.InsertionSort;
import sorting.algorithms.MergeSort;
import sorting.algorithms.QuickSort;

public class SortStrategyFactory {

    public <T extends Character> SortStrategy<T> createSortStrategy(Algorithm algorithm) {
        return switch (algorithm) {
            case INSERTION_SORT -> new InsertionSort<>();
            case BUBBLE_SORT -> new BubbleSort<>();
            case MERGE_SORT -> new MergeSort<>();
            case QUICK_SORT -> new QuickSort<>();
        };
    }
}

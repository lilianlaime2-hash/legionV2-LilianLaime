package sorting;

import model.Algorithm;

public class SortStrategyFactory {

    public <T> SortStrategy<T> resolve(Algorithm algorithm) {

        return switch (algorithm) {
            case INSERTION_SORT -> new InsertionSort<>();
            case BUBBLE_SORT -> new BubbleSort<>();
            case MERGE_SORT -> new MergeSort<>();
            case QUICK_SORT -> new QuickSort<>();
        };
    }
}
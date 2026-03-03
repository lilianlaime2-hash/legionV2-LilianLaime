package sorting;

import model.Algorithm;
import model.Character;

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

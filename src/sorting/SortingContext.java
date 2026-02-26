package sorting;

import model.Orientation;

import java.util.Comparator;

public class SortingContext<T> {

    private final SortStrategy<T> strategy;
    private final Comparator<T> comparator;
    private final Orientation orientation;

    public SortingContext(SortStrategy<T> strategy,
                          Comparator<T> comparator,
                          Orientation orientation) {
        this.strategy = strategy;
        this.comparator = comparator;
        this.orientation = orientation;
    }

    public SortStrategy<T> getStrategy() {
        return strategy;
    }

    public Comparator<T> getComparator() {
        return comparator;
    }

    public Orientation getOrientation() {
        return orientation;
    }
}
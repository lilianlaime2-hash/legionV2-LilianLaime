package sorting;

import model.Character;
import model.Orientation;

import java.util.Comparator;

public class SortingContext {

    private final SortStrategy<Character> strategy;
    private final Comparator<Character> comparator;
    private final Orientation orientation;

    public SortingContext(SortStrategy<Character> strategy, Comparator<Character> comparator, Orientation orientation) {
        this.strategy = strategy;
        this.comparator = comparator;
        this.orientation = orientation;
    }

    public SortStrategy<Character> getStrategy() {
        return strategy;
    }

    public Comparator<Character> getComparator() {
        return comparator;
    }

    public Orientation getOrientation() {
        return orientation;
    }
}
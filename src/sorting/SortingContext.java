package sorting;

import creation.model.Orientation;
import creation.model.character.Character;

import java.util.Comparator;

public class SortingContext {

    private final SortStrategy strategy;
    private final Comparator<Character> comparator;
    private final Orientation orientation;

    public SortingContext(SortStrategy strategy,
                          Comparator<Character> comparator,
                          Orientation orientation) {
        this.strategy = strategy;
        this.comparator = comparator;
        this.orientation = orientation;
    }

    public SortStrategy getStrategy() {
        return strategy;
    }

    public Comparator<Character> getComparator() {
        return comparator;
    }

    public Orientation getOrientation() {
        return orientation;
    }
}

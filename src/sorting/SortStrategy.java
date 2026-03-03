package sorting;

import creation.model.character.Character;

import java.util.Comparator;
import java.util.List;

public interface SortStrategy<T extends Character> {
    /**
     * Sorts the given list using the provided comparator.
     * The observer is notified after each visible change in the list.
     */
    void sort(
            List<T> list,
            Comparator<T> comparator,
            ConsoleSortObserver<T> observer
    );
}

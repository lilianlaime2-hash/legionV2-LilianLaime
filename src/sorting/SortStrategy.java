package sorting;

import creation.model.character.Character;

import java.util.Comparator;
import java.util.List;

public interface SortStrategy {
    /**
     * Sorts the given list using the provided comparator.
     * The observer is notified after each visible change in the list.
     */
    void sort(List<Character> list, Comparator<Character> comparator, ConsoleSortObserver observer);
}

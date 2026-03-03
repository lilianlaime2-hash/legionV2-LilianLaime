package sorting;

import java.util.Comparator;
import java.util.List;

public interface SortStrategy<T> {
    // Sorts the given list using the provided comparator.
    void sort(List<T> list, Comparator<T> comparator);
}

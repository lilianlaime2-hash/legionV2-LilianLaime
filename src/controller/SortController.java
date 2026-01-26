package controller;

import model.Character;
import sort.SortStrategy;
import java.util.List;

public class SortController {

    private SortStrategy strategy;

    public void setStrategy(SortStrategy strategy) {
        this.strategy = strategy;
    }

    public void executeSort(List<Character> troops) {
        if (strategy != null) {
            strategy.sort(troops);
        }
    }
}

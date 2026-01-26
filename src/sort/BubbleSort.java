package sort;

import model.Character;
import java.util.List;

public class BubbleSort implements SortStrategy {

    @Override
    public void sort(List<Character> troops) {
        for (int i = 0; i < troops.size(); i++) {
            for (int j = 0; j < troops.size() - 1; j++) {
                if (troops.get(j).getRank() > troops.get(j + 1).getRank()) {
                    var temp = troops.get(j);
                    troops.set(j, troops.get(j + 1));
                    troops.set(j + 1, temp);
                }
            }
        }
    }
}

package sorting;

import creation.model.character.Character;

import java.util.List;

public class ConsoleSortObserver {

    private final int interval;
    private final String type;
    private int steps;

    public ConsoleSortObserver(int interval, String type) {
        this.interval = interval;
        this.type = type;
        this.steps = 0;
    }

    public void notifyStep(List<Character> state) {
        steps++;
        if (steps % interval == 0) {
            printTroops(state);
            pauseStep();
        }
    }

    public int getSteps() {
        return steps;
    }

    public void printTroops(List<Character> troops) {
        StringBuilder output = new StringBuilder("[");
        for (int i = 0; i < troops.size(); i++) {
            Character troop = troops.get(i);
            if ("n".equals(type)) {
                output.append(troop.getValue());
            } else {
                output.append(troop.getSymbol());
            }
            if (i < troops.size() - 1) {
                output.append(", ");
            }
        }
        output.append("]");
        System.out.println(output);
    }

    private void pauseStep() {
        try {
            Thread.sleep(200);
        } catch (InterruptedException ignored) {
            Thread.currentThread().interrupt();
        }
    }
}

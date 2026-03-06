package orchestration;

import creation.TroopPlacer;
import creation.model.BattleField;
import creation.model.Cell;
import creation.model.character.Character;
import sorting.ConsoleSortObserver;
import sorting.SortingContext;

import java.util.ArrayList;
import java.util.List;

public class BattleFieldService {

    private static final int STEP_PRINT_INTERVAL = 1;
    private final TroopPlacer troopPlacer;

    public BattleFieldService(TroopPlacer troopPlacer) {
        this.troopPlacer = troopPlacer;
    }

    public void sort(BattleField battleField, SortingContext context, String type) {

        List<Character> troops = extractTroops(battleField);
        ConsoleSortObserver observer = new ConsoleSortObserver(STEP_PRINT_INTERVAL, type);

        System.out.println("\nSorting steps:");

        observer.printTroops(troops);

        context.getStrategy().sort(troops, context.getComparator(), observer);

        boolean needsFinalPrint = observer.getSteps() > 0 && observer.getSteps() % STEP_PRINT_INTERVAL != 0;
        if (needsFinalPrint) {
            observer.printTroops(troops);
        }

        troopPlacer.placeSorted(battleField, troops, context.getOrientation());
    }

    private List<Character> extractTroops(BattleField battleField) {

        List<Character> troops = new ArrayList<>();
        int size = battleField.getSize();

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {

                Cell cell = battleField.getCell(i, j);
                if (!cell.isEmpty()) troops.add(cell.getCharacter());
            }
        }

        return troops;
    }

}

package service;

import model.BattleField;
import model.Cell;
import model.Character;
import sorting.SortingContext;

import java.util.ArrayList;
import java.util.List;

public class BattleFieldService {

    private final TroopPlacer troopPlacer;

    public BattleFieldService(TroopPlacer troopPlacer) {
        this.troopPlacer = troopPlacer;
    }

    public void sort(BattleField battleField, SortingContext<Character> context) {

        List<Character> troops = extractTroops(battleField);

        context.getStrategy().sort(troops, context.getComparator());

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

package service;

import model.BattleField;
import model.Character;
import model.Orientation;
import model.TroopType;

import java.util.List;
import java.util.Random;

public class TroopPlacer {

    private final Random random = new Random();

    public void placeRandom(BattleField battleField, List<Character> troops, int size) {

        for (Character troop : troops) {

            int row;
            int col;

            do {
                row = random.nextInt(size);
                col = random.nextInt(size);
            } while (!battleField.getCell(row, col).isEmpty());

            battleField.placeCharacter(troop, row, col);
        }
    }

    public void placeSorted(BattleField battleField, List<Character> troops, Orientation orientation) {
        battleField.clear();

        TroopType previousType = troops.get(0).getType();
        int currentLine = 0;
        int positionInLine = 0;
        int size = battleField.getSize();

        for (Character troop : troops) {
            boolean lineIsFull = positionInLine >= size;
            if (lineIsFull) {
                currentLine++;
                positionInLine = 0;
            }

            boolean isDifferentTroopType = troop.getType() != previousType;
            if (isDifferentTroopType) {
                boolean isCurrentLineBusy = positionInLine != 0;
                if (isCurrentLineBusy) {
                    currentLine++;
                }
                positionInLine = 0;
                previousType = troop.getType();
            }

            int targetRow = 0;
            int targetCol = 0;

            switch (orientation) {
                case EAST:
                    targetRow = (size - 1) - positionInLine;
                    targetCol = currentLine;
                    break;
                case WEST:
                    targetRow = (size - 1) - positionInLine;
                    targetCol = (size - 1) - currentLine;
                    break;
                case NORTH:
                    targetRow = (size - 1) - currentLine;
                    targetCol = positionInLine;
                    break;
                case SOUTH:
                    targetRow = currentLine;
                    targetCol = positionInLine;
                    break;
            }

            battleField.placeCharacter(troop, targetRow, targetCol);

            positionInLine++;
        }
    }
}

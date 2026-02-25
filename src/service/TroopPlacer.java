package service;

import model.BattleField;
import model.Character;

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
}
package view;

import model.*;
import model.Character;

public class BattleFieldView {

    public void print(BattleField battleField, String type) {

        int size = battleField.getSize();

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {

                Cell cell = battleField.getCell(i, j);

                if (cell.isEmpty()) {
                    System.out.print(" * ");
                } else {
                    Character c = cell.getCharacter();
                    if ("n".equals(type)) {
                        System.out.print(" " + c.getType().getMin() + " ");
                    } else {
                        System.out.print(" " + c + " ");
                    }
                }
            }
            System.out.println();
        }
    }
}
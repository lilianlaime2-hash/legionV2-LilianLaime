package view;

import model.BattleField;
import model.Cell;
import model.Character;

public class BattleFieldView {

    public void print(BattleField battleField, String type) {
        int size = battleField.getSize();

        for (int i = 0; i < size; i++) {
            String row = "";

            for (int j = 0; j < size; j++) {
                Cell cell = battleField.getCell(i, j);

                if (cell.isEmpty()) {
                    row += "*";
                } else {
                    Character character = cell.getCharacter();
                    if ("n".equals(type)) {
                        row += character.getValue();
                    } else row += character.getSymbol();
                }

                row += " ";
            }

            System.out.println(row);
        }
    }
}

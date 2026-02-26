package view;

import model.BattleField;
import model.Cell;
import model.Character;

public class BattleFieldView {

    public void print(BattleField battleField, String type) {
        int size = battleField.getSize();

        for (int i = 0; i < size; i++) {
            StringBuilder sb = new StringBuilder();

            for (int j = 0; j < size; j++) {
                Cell cell = battleField.getCell(i, j);

                if (cell.isEmpty()) {
                    sb.append("*");
                } else {
                    Character c = cell.getCharacter();
                    if ("n".equals(type)) sb.append(c.getValue());
                    else sb.append(c.getSymbol());
                }

                if (j < size - 1) sb.append(" ");
            }

            System.out.println(sb);
        }
    }
}
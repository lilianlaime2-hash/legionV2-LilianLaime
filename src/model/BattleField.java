package model;

import java.util.ArrayList;
import java.util.List;

public class BattleField {

    private Cell[][] fieldMatrix;
    private List<Character> troops = new ArrayList<>();

    public BattleField(int size) {
        fieldMatrix = new Cell[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                fieldMatrix[i][j] = new Cell(i, j);
            }
        }
    }

    public void placeTroop(Character character) {
        troops.add(character);
    }

    public List<Character> getTroops() {
        return troops;
    }

    public Cell[][] getFieldMatrix() {
        return fieldMatrix;
    }

    public void showBattleField() {
        System.out.println("Battlefield displayed");
    }
}

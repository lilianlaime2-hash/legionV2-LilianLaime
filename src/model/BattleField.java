package model;

import java.util.List;

public class BattleField {

    private final Cell[][] grid;

    public BattleField(int size) {

        grid = new Cell[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                grid[i][j] = new Cell();
            }
        }
    }

    public int getSize() {
        return grid.length;
    }

    public Cell getCell(int row, int column) {
        return grid[row][column];
    }

    public void placeCharacter(Character character, int row, int column) {
        grid[row][column].setCharacter(character);
    }

    public void clear() {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                grid[i][j].setCharacter(null);
            }
        }
    }

    public void fillSorted(List<Character> troops, Orientation orientation) {

        if (troops.isEmpty()) {
            return;
        }

        TroopType prevType = troops.get(0).getType();
        int line = 0;
        int position = 0;
        int size = grid.length;

        clear();

        for (Character troop : troops) {

            if (position >= size) {
                line++;
                position = 0;
            }

            if (troop.getType() != prevType) {
                if (position != 0) {
                    line++;
                }
                position = 0;
                prevType = troop.getType();
            }

            int row = 0;
            int col = 0;

            switch (orientation) {
                case EAST:
                    row = (size - 1) - position;
                    col = line;
                    break;
                case WEST:
                    col = (size - 1) - line;
                    row = (size - 1) - position;
                    break;
                case NORTH:
                    row = (size - 1) - line;
                    col = position;
                    break;
                case SOUTH:
                    row = line;
                    col = position;
                    break;
            }

            if (row < 0 || row >= size || col < 0 || col >= size) {
                position++;
                continue;
            }

            grid[row][col].setCharacter(troop);
            position++;
        }
    }
}
package model;

import java.util.List;

public class BattleField {

    private final int size;
    private final Cell[][] grid;

    public BattleField(int size) {
        this.size = size;
        this.grid = new Cell[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) grid[i][j] = new Cell();
        }
    }

    public int getSize() { return size; }

    public Cell getCell(int row, int col) {
        return grid[row][col];
    }

    public void placeCharacter(Character c, int row, int col) {
        grid[row][col].setCharacter(c);
    }

    public void clear() {
        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++)
                grid[i][j].clear();
    }

    public void fillSorted(List<Character> troops, Orientation orientation) {

        if (troops == null || troops.isEmpty()) {
            return;
        }

        clear();

        TroopType prevType = troops.get(0).getType();
        int line = 0;
        int position = 0;
        int size = getSize();

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

            if (row >= 0 && row < size && col >= 0 && col < size) {
                placeCharacter(troop, row, col);
            }

            position++;
        }
    }

}
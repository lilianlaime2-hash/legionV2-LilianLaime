package creation.model;

import creation.model.character.Character;

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
}

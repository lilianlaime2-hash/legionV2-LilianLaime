package model;

public class Cell {

    private Character character;
    private int row;
    private int column;

    public Cell(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public boolean isEmpty() {
        return character == null;
    }

    public void setCharacter(Character character) {
        this.character = character;
        character.move(row, column);
    }

    public Character getCharacter() {
        return character;
    }

    public void clear() {
        character = null;
    }
}

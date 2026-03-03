package model;

public class Cell {

    private Character character;

    public boolean isEmpty() {
        return character == null;
    }

    public Character getCharacter() {
        return character;
    }

    public void setCharacter(Character character) {
        this.character = character;
    }

    public void clear() {
        this.character = null;
    }
}
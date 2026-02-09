package model;

public class Cell {
    private int row;
    private int column;
    private Character character = null;

    public Cell (int row, int column){
        this.row = row;
        this.column = column;
    }

    public boolean isEmpty(){
        if(character == null){
            return true;
        }
        return false;
    }

    public void setCharacter (Character character){
        this.character = character;
    }

    public Character getCharacter (){
        return character;
    }
}

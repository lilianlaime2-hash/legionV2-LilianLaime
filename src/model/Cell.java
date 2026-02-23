package model;

public class Cell {

    private Character character = null;

    public boolean isEmpty(){
        if (character == null){
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
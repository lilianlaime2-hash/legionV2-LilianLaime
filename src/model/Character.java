package model;

public abstract class Character {

    private final int value;
    private final TroopType type;

    protected Character(int value, TroopType type) {
        this.value = value;
        this.type = type;
    }

    public int getValue() { return value; }
    public TroopType getType() { return type; }
    public char getSymbol() { return type.getSymbol(); }
}
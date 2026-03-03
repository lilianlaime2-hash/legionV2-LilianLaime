package model;

public abstract class Character {

    private final int value;
    private final TroopType type;

    protected Character(int value, TroopType type) {
        this.value = value;
        this.type = type;
    }

    // Returns the combat value of the troop.
    public int getValue() { return value; }
    // Returns the troop category.
    public TroopType getType() { return type; }
    // Returns the printable symbol of the troop type.
    public char getSymbol() { return type.getSymbol(); }
}

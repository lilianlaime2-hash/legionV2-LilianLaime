package model;

public abstract class Character {

    private final TroopType type;
    private final int value;

    public Character(TroopType type, int value) {
        this.type = type;
        this.value = value;
    }

    public TroopType getType() {
        return type;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(type.getSymbol());
    }
}
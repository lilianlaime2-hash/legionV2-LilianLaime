package model;

public abstract class Character {
    private int health;
    private int speed;
    private int rank;
    private int row;
    private int column;
    private final TroopType type;

    public Character(TroopType type) {
        this.type = type;
    }

    @Override
    public String toString(){
        return String.valueOf(type.getSymbol());
    }

    public TroopType getType() {
        return type;
    }
}

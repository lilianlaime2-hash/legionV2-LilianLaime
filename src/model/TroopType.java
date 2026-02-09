package model;

public enum TroopType {
    COMMANDER('C',0),
    MEDIC('M',10),
    TANK('T', 20),
    SNIPER('S', 30),
    INFANTRY('I',40);

    private final int value;
    private final char symbol;

    TroopType(char symbol, int value) {
        this.value = value;
        this.symbol = symbol;
    }

    public char getSymbol() {
        return symbol;
    }

    public int getValue() {
        return value;
    }
}

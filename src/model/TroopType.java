package model;

public enum TroopType {
    COMMANDER(0, 'C'),
    MEDIC(10, 'M'),
    TANK(20, 'T'),
    SNIPER(30, 'S'),
    INFANTRY(40, 'I');

    private final int min;
    private final char symbol;

    TroopType(int min, char symbol) {
        this.min = min;
        this.symbol = symbol;
    }

    public int getMin() { return min; }
    public char getSymbol() { return symbol; }
}

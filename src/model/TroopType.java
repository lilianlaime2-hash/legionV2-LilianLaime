package model;

public enum TroopType {

    COMMANDER('C', 0, 9),
    MEDIC('M', 10, 19),
    TANK('T', 20, 29),
    SNIPER('S', 30, 39),
    INFANTRY('I', 40, 49);

    private final char symbol;
    private final int min;
    private final int max;

    TroopType(char symbol, int min, int max) {
        this.symbol = symbol;
        this.min = min;
        this.max = max;
    }

    public char getSymbol() {
        return symbol;
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }

}
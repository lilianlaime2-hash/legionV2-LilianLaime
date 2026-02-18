package model;

public enum TroopType {

    COMMANDER('C', 0, 10),
    MEDIC('M', 11, 20),
    TANK('T', 21, 30),
    SNIPER('S', 31, 40),
    INFANTRY('I', 41, 50);

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

    // =========================
    // NUMERIC RANGE MAPPING
    // =========================
    public static TroopType fromNumber(int value) {

        if (value >= 0 && value <= 10) return COMMANDER;
        if (value >= 11 && value <= 20) return MEDIC;
        if (value >= 21 && value <= 30) return TANK;
        if (value >= 31 && value <= 40) return SNIPER;
        if (value >= 41 && value <= 50) return INFANTRY;

        throw new IllegalArgumentException("Invalid numeric range");
    }

    // =========================
    // CHARACTER RANGE MAPPING
    // =========================
    public static TroopType fromCharacter(char c) {

        if (c >= 'a' && c <= 'j')
            return COMMANDER;

        if (c >= 'k' && c <= 't')
            return MEDIC;

        if ((c >= 'u' && c <= 'z') || (c >= 'A' && c <= 'J'))
            return TANK;

        if (c >= 'K' && c <= 'N')
            return SNIPER;

        if (c >= 'O' && c <= 'X')
            return INFANTRY;

        throw new IllegalArgumentException("Invalid character range");
    }

    public char toMappedChar(int value) {

        switch (this) {
            case COMMANDER:
                return (char) ('a' + (value - min));
            case MEDIC:
                return (char) ('k' + (value - min));
            case TANK:
                return (char) ('u' + (value - min));
            case SNIPER:
                return (char) ('K' + (value - min));
            case INFANTRY:
                return (char) ('O' + (value - min));
            default:
                throw new IllegalArgumentException();
        }
    }
}
package model;

public enum Orientation {
    NORTH("n", "North"),
    SOUTH("s", "Sud"),
    EAST("e", "East"),
    WEST("w", "west");

    private final String symbol;
    private final String longName;

    Orientation(String symbol, String longName) {
        this.symbol = symbol;
        this.longName = longName;
    }

    public String getLongName() {
        return longName;
    }

    public static Orientation fromSymbol(String s) {
        String v = s.toLowerCase();
        for (Orientation o : values()) {
            if (o.symbol.equals(v)) return o;
        }
        throw new IllegalArgumentException("Invalid orientation: " + s);
    }
}
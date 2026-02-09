package model;

public enum Orientation {
    EAST("e", "East"),
    WEST("w", "West"),
    NORTH("n", "North"),
    SOUTH("s", "South");

    private final String symbol;
    private final String longName;

    Orientation(String symbol, String longName){
        this.symbol = symbol;
        this.longName = longName;
    }

    public static Orientation fromSymbol(String symbol) {
        for (Orientation o : values()) {
            if (o.symbol.equals(symbol)) {
                return o;
            }
        }
        throw new IllegalArgumentException("Invalid algorithm");
    }

    public String getLongName(){
        return this.longName;
    }

}

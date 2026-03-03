package creation.model;

public enum Orientation {
    NORTH("n", "North"),
    SOUTH("s", "Sud"),
    EAST("e", "East"),
    WEST("w", "west");

    private final String abbreviation;
    private final String longName;

    Orientation(String abbreviation, String longName) {
        this.abbreviation = abbreviation;
        this.longName = longName;
    }

    public String getLongName() {
        return longName;
    }

    public static Orientation translateAbbreviation(String abbreviation) {
        for (Orientation orientation : values()) {
            if (orientation.abbreviation.equals(abbreviation)) return orientation;
        }
        throw new IllegalArgumentException("Invalid orientation: " + abbreviation);
    }
}

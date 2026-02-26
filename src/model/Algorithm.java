package model;

public enum Algorithm {
    INSERTION_SORT("i", "Insertion sort"),
    BUBBLE_SORT("b", "Bubble sort"),
    MERGE_SORT("m", "Merge sort"),
    QUICK_SORT("q", "Quick sort");

    private final String symbol;
    private final String longName;

    Algorithm(String symbol, String longName) {
        this.symbol = symbol;
        this.longName = longName;
    }

    public String getLongName() {
        return longName;
    }

    public static Algorithm fromSymbol(String s) {
        String v = s.toLowerCase();
        for (Algorithm a : values()) {
            if (a.symbol.equals(v)) return a;
        }
        throw new IllegalArgumentException("Invalid algorithm: " + s);
    }
}
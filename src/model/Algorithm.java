package model;

public enum Algorithm {
    INSERTION_SORT("i", "Insertion sort"),
    BUBBLE_SORT("b", "Bubble sort"),
    MERGE_SORT("m", "Merge sort"),
    QUICK_SORT("q", "Quick sort");

    private final String abbreviation;
    private final String longName;

    Algorithm(String abbreviation, String longName) {
        this.abbreviation = abbreviation;
        this.longName = longName;
    }

    public String getLongName() {
        return longName;
    }

    public static Algorithm translateAbbreviation(String abbreviation) {
        for (Algorithm algorithm : values()) {
            if (algorithm.abbreviation.equals(abbreviation)) return algorithm;
        }
        throw new IllegalArgumentException("Invalid algorithm: " + abbreviation);
    }
}

package model;

public enum Algorithm {
    INSERTION_SORT("i", "Insertion Sort"),
    BUBBLE_SORT("b", "Buble Sort"),
    QUICK_SORT("q", "Quick Sort"),
    MERGE_SORT("m", "Merge Sort");

    private final String symbol;
    private final String longName;

    Algorithm(String symbol, String longName){
        this.symbol = symbol;
        this.longName = longName;
    }

    public String getSymbol(){
        return this.symbol;
    }

    public String getLongName(){
        return this.longName;
    }

    public static Algorithm fromSymbol(String code) {
        for (Algorithm a : values()) {
            if (a.symbol.equals(code)) {
                return a;
            }
        }
        throw new IllegalArgumentException("Invalid algorithm");
    }

}

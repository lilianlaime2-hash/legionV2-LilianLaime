package validation.syntax;

import creation.model.GameConfig;
import creation.model.Orientation;
import sorting.Algorithm;

import java.util.Map;

public class ParseReport {

    private final GameConfig config;
    private final Map<String, FieldStatus> statuses;

    public ParseReport(GameConfig config, Map<String, FieldStatus> statuses) {
        this.config = config;
        this.statuses = statuses;
    }

    public GameConfig getConfig() {
        return config;
    }

    public boolean hasErrors() {
        return isInvalid("a")
                || isInvalid("t")
                || isInvalid("u")
                || isInvalid("o")
                || isInvalid("f")
                || isMissing("a")
                || isMissing("t")
                || isMissing("u");
    }

    private boolean isInvalid(String key) {
        FieldStatus status = statuses.get(key);
        return status == FieldStatus.INVALID;
    }

    private boolean isMissing(String key) {
        FieldStatus status = statuses.get(key);
        return status == FieldStatus.NOT_PRESENT;
    }

    public void printState() {
        System.out.println("Algorithm: [" + show(statuses.get("a"), getDisplayableAlgorithm()) + "]");
        System.out.println("Type: [" + show(statuses.get("t"), getDisplayableType()) + "]");
        System.out.println("Orientation: [" + show(statuses.get("o"), getDisplayableOrientation()) + "]");
        System.out.println("Troops: [" + show(statuses.get("u"), getDisplayableTroops()) + "]");
        System.out.println("Battlefield: [" + show(statuses.get("f"), getDisplayableBattlefield()) + "]");
    }

    private String show(FieldStatus status, String value) {
        if (status == null || status == FieldStatus.NOT_PRESENT) return "Not present";
        if (status == FieldStatus.INVALID) return "Invalid";
        return value;
    }


    private String getDisplayableAlgorithm() {
        Algorithm a = config.getAlgorithm();
        return a != null ? a.getLongName() : "";
    }

    private String getDisplayableType() {
        String t = config.getType();
        if (t == null) {
            return "";
        }
        return t.equals("c") ? "Character" : "Number";
    }

    private String getDisplayableOrientation() {
        Orientation o = config.getOrientation();
        return o != null ? o.getLongName() : "";
    }

    private String getDisplayableTroops() {
        int[] u = config.getUnits();
        if (u == null) {
            return "";
        }
        int troopsCount = config.sumUnits();
        return String.valueOf(troopsCount);
    }

    private String getDisplayableBattlefield() {
        int f = config.getFieldSize();
        return f + " x " + f;
    }
}

package util;

import model.Algorithm;
import model.Orientation;

public class ParseReport {

    private final GameConfig config;

    private final FieldStatus aStatus;
    private final FieldStatus tStatus;
    private final FieldStatus oStatus;
    private final FieldStatus uStatus;
    private final FieldStatus fStatus;

    public ParseReport(GameConfig config,
                       FieldStatus aStatus,
                       FieldStatus tStatus,
                       FieldStatus oStatus,
                       FieldStatus uStatus,
                       FieldStatus fStatus) {
        this.config = config;
        this.aStatus = aStatus;
        this.tStatus = tStatus;
        this.oStatus = oStatus;
        this.uStatus = uStatus;
        this.fStatus = fStatus;
    }

    public GameConfig getConfig() {
        return config;
    }

    public boolean hasFatalErrors() {
        return aStatus == FieldStatus.INVALID
                || tStatus == FieldStatus.INVALID
                || uStatus == FieldStatus.INVALID
                || fStatus == FieldStatus.INVALID
                || config.getAlgorithm() == null
                || config.getType() == null
                || config.getUnits() == null;
    }

    public void printState() {

        Algorithm a = config.getAlgorithm();
        String t = config.getType();
        Orientation o = config.getOrientation();
        int[] u = config.getUnits();
        int f = config.getFieldSize();

        System.out.println("Algorithm: [" + show(aStatus, a != null ? a.getLongName() : "") + "]");
        System.out.println("Type: [" + show(tStatus, t != null ? (t.equals("c") ? "Character" : "Number") : "") + "]");
        System.out.println("Orientation: [" + show(oStatus, o != null ? o.getLongName() : "") + "]");

        int troopsCount = 0;
        if (u != null) {
            for (int x : u) troopsCount += x;
        }

        System.out.println("Troops: [" + show(uStatus, u != null ? String.valueOf(troopsCount) : "") + "]");
        System.out.println("Battlefield: [" + show(fStatus, f + " x " + f) + "]");
    }

    private String show(FieldStatus status, String value) {
        if (status == FieldStatus.NOT_PRESENT) return "Not present";
        if (status == FieldStatus.INVALID) return "Invalid";
        return value;
    }
}
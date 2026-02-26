package util;

import model.Algorithm;
import model.Orientation;

import java.util.Arrays;

public final class GameConfig {

    private final Algorithm algorithm;
    private final String type;
    private final Orientation orientation;
    private final int[] units;
    private final int fieldSize;

    public GameConfig(
            Algorithm algorithm,
            String type,
            Orientation orientation,
            int[] units,
            int fieldSize) {

        this.algorithm = algorithm;
        this.type = type;
        this.orientation = orientation;
        this.units = units == null ? null : Arrays.copyOf(units, units.length);
        this.fieldSize = fieldSize;
    }

    public Algorithm getAlgorithm() {
        return algorithm;
    }

    public String getType() {
        return type;
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public int[] getUnits() {
        return units == null ? null : Arrays.copyOf(units, units.length);
    }

    public int getFieldSize() {
        return fieldSize;
    }
}
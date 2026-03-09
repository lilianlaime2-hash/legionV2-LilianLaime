package validation.syntax;

import creation.model.GameConfig;
import creation.model.Orientation;
import sorting.Algorithm;

import java.util.HashMap;
import java.util.Map;

public class CliParser {
    private Map<String, String> paramMap;
    private Map<String, FieldStatus> statuses;

    public ParseReport parse(String[] args) {

        Algorithm algorithm = null;
        String type = null;
        Orientation orientation = Orientation.SOUTH;
        int[] units = null;
        int fieldSize = 10;

        statuses = new HashMap<>();
        statuses.put("a", FieldStatus.NOT_PRESENT);
        statuses.put("t", FieldStatus.NOT_PRESENT);
        statuses.put("o", FieldStatus.NOT_PRESENT);
        statuses.put("u", FieldStatus.NOT_PRESENT);
        statuses.put("f", FieldStatus.NOT_PRESENT);

        paramMap = new HashMap<>();

        if (args == null || args.length == 0) {
            return new ParseReport(new GameConfig(algorithm, type, orientation, units, fieldSize), statuses);
        }

        buildMap(args);

        if (paramMap.containsKey("a")) {
            algorithm = parseAlgorithm();
        }
        if (paramMap.containsKey("t")) {
            type = parseType();
        }
        if (paramMap.containsKey("o")) {
            orientation = parseOrientation();
        }
        if (paramMap.containsKey("u")) {
            units = parseUnits();
        }
        if (paramMap.containsKey("f")) {
            fieldSize = parseFieldSize(fieldSize);
        }

        return new ParseReport(new GameConfig(algorithm, type, orientation, units, fieldSize), statuses);
    }

    private void buildMap(String[] args) {
        for (String arg : args) {
            arg = arg.trim();
            if (arg.isEmpty()) continue;
            String[] parts = arg.split("=", 2);

            String key = parts[0].trim().toLowerCase();
            if (key.equals("r")) key = "u";
            if (parts.length != 2) {
                if (statuses.containsKey(key)) {
                    statuses.put(key, FieldStatus.INVALID);
                }
                continue;
            }

            String value = parts[1].trim().toLowerCase();
            paramMap.put(key, value);
        }
    }

    private Algorithm parseAlgorithm() {
        try {
            Algorithm algorithm = Algorithm.translateAbbreviation(paramMap.get("a"));
            statuses.put("a", FieldStatus.VALID);
            return algorithm;
        } catch (Exception ignored) {
            statuses.put("a", FieldStatus.INVALID);
            return null;
        }
    }

    private String parseType() {
        String type = paramMap.get("t");
        if (type.equals("c") || type.equals("n")) {
            statuses.put("t", FieldStatus.VALID);
            return type;
        }
        statuses.put("t", FieldStatus.INVALID);
        return null;
    }

    private Orientation parseOrientation() {
        try {
            Orientation orientation = Orientation.translateAbbreviation(paramMap.get("o"));
            statuses.put("o", FieldStatus.VALID);
            return orientation;
        } catch (Exception ignored) {
            statuses.put("o", FieldStatus.INVALID);
            return Orientation.SOUTH;
        }
    }

    private int[] parseUnits() {

        try {
            String troops = paramMap.get("u");
            String[] troopsText = troops.split(",");
            int[] troopsInt = new int[troopsText.length];

            for (int i = 0; i < troopsText.length; i++){
                int parsedTroop = Integer.parseInt(troopsText[i].trim());

                if(parsedTroop < 0){
                    statuses.put("u", FieldStatus.INVALID);
                    return null;
                }
                troopsInt[i] = parsedTroop;
            }

            statuses.put("u", FieldStatus.VALID);
            return troopsInt;

        } catch (Exception ignored){
            statuses.put("u", FieldStatus.INVALID);
            return null;
        }
    }

    private int parseFieldSize(int defaultFieldSize) {
        try {
            String size = paramMap.get("f");
            int parsedSize = Integer.parseInt(size);

            if (parsedSize >= 5 && parsedSize <= 1000){
                statuses.put("f", FieldStatus.VALID);
                return parsedSize;
            }
        } catch (Exception ignored){
            statuses.put("f", FieldStatus.INVALID);
        }
        return defaultFieldSize;
    }
}

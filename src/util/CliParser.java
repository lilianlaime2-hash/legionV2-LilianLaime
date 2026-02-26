package util;

import model.Algorithm;
import model.Orientation;

import java.util.Arrays;

public class CliParser {

    public ParseReport parse(String[] args) {

        // Defaults (como tu versión anterior)
        Algorithm a = null;
        String t = null;
        Orientation o = Orientation.SOUTH;
        int[] u = null;
        int f = 10;

        FieldStatus aS = FieldStatus.NOT_PRESENT;
        FieldStatus tS = FieldStatus.NOT_PRESENT;
        FieldStatus oS = FieldStatus.NOT_PRESENT;
        FieldStatus uS = FieldStatus.NOT_PRESENT;
        FieldStatus fS = FieldStatus.VALID; // igual que antes (por default 10 era válido)

        if (args == null || args.length == 0) {
            GameConfig cfg = new GameConfig(a, t, o, u, f);
            return new ParseReport(cfg, aS, tS, oS, uS, FieldStatus.NOT_PRESENT);
        }

        for (String arg : args) {

            String[] parts = arg.split("=");
            if (parts.length != 2) {
                // igual a tu lógica anterior: marca varios como inválidos
                aS = FieldStatus.INVALID;
                tS = FieldStatus.INVALID;
                uS = FieldStatus.INVALID;
                // f y o quedan como "válidos" por default / no se invalidan aquí
                continue;
            }

            String key = parts[0].trim().toLowerCase();
            String value = parts[1].trim().toLowerCase();

            switch (key) {

                case "a" -> {
                    try {
                        a = Algorithm.fromSymbol(value);
                        aS = FieldStatus.VALID;
                    } catch (Exception e) {
                        aS = FieldStatus.INVALID;
                    }
                }

                case "t" -> {
                    if (value.equals("c") || value.equals("n")) {
                        t = value;
                        tS = FieldStatus.VALID;
                    } else {
                        tS = FieldStatus.INVALID;
                    }
                }

                case "o" -> {
                    try {
                        o = Orientation.fromSymbol(value);
                        oS = FieldStatus.VALID;
                    } catch (Exception e) {
                        // igual que antes: si falla, se marca inválida pero se conserva default SOUTH
                        oS = FieldStatus.INVALID;
                    }
                }

                case "u", "r" -> {
                    try {
                        u = Arrays.stream(value.split(","))
                                .map(String::trim)
                                .mapToInt(Integer::parseInt)
                                .toArray();
                        uS = FieldStatus.VALID;
                    } catch (Exception e) {
                        uS = FieldStatus.INVALID;
                    }
                }

                case "f" -> {
                    try {
                        f = Integer.parseInt(value);
                        if (f >= 5 && f <= 1000) {
                            fS = FieldStatus.VALID;
                        } else {
                            fS = FieldStatus.INVALID;
                        }
                    } catch (Exception e) {
                        fS = FieldStatus.INVALID;
                    }
                }
            }
        }

        GameConfig cfg = new GameConfig(a, t, o, u, f);
        return new ParseReport(cfg, aS, tS, oS, uS, fS);
    }
}
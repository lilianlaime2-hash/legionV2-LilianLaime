package util;

import model.Algorithm;
import model.Orientation;

import java.util.Arrays;

public class ParametersParser {

    public void parseInto(Parameters p, String[] args) {

        if (args == null || args.length == 0) {
            p.aValid = null;
            p.tValid = null;
            p.uValid = null;
            p.fValid = null;
            p.oValid = null;
            return;
        }

        for (String arg : args) {

            String[] parts = arg.split("=");
            if (parts.length != 2) {
                p.aValid = false;
                p.tValid = false;
                p.uValid = false;
                p.fValid = true;
                p.oValid = true;
                continue;
            }

            String key = parts[0].trim().toLowerCase();
            String value = parts[1].trim().toLowerCase();

            switch (key) {

                case "a":
                    try {
                        p.a = Algorithm.fromSymbol(value);
                        p.aValid = true;
                    } catch (Exception e) {
                        p.aValid = false;
                    }
                    break;

                case "t":
                    if (value.equals("c") || value.equals("n")) {
                        p.t = value;
                        p.tValid = true;
                    } else {
                        p.tValid = false;
                    }
                    break;

                case "o":
                    try {
                        p.o = Orientation.fromSymbol(value);
                        p.oValid = true;
                    } catch (Exception e) {
                        p.oValid = false;
                    }
                    break;

                case "u":
                case "r":
                    try {
                        p.u = Arrays.stream(value.split(","))
                                .map(String::trim)
                                .mapToInt(Integer::parseInt)
                                .toArray();
                        p.uValid = true;
                    } catch (Exception e) {
                        p.uValid = false;
                    }
                    break;

                case "f":
                    try {
                        p.f = Integer.parseInt(value);
                        if (p.f >= 5 && p.f <= 1000) {
                            p.fValid = true;
                        } else {
                            p.fValid = false;
                        }
                    } catch (Exception e) {
                        p.fValid = false;
                    }
                    break;

            }
        }
    }
}
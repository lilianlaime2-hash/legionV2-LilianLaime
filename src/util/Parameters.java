package util;

import model.Algorithm;
import model.Orientation;

import java.util.Arrays;

public class Parameters {

    public Algorithm algorithm;
    public String t;
    public Orientation orientation;
    public int[] u;
    public int f = 6;

    public Boolean aValid;
    public Boolean tValid;
    public Boolean uValid;
    public Boolean fValid;
    public Boolean oValid;

    public String invalidField;
    public String errorMessage;

    public boolean validate(String[] args) {

        if (args == null || args.length == 0) {
            invalidField = "Arguments";
            errorMessage = "Arguments are invalid";
            return false;
        }

        for (String arg: args) {

            String[] parts = arg.split("=");
            if (parts.length != 2) {
                invalidField = "Arguments";
                errorMessage = "Arguments format is invalid";
                return false;
            }

            String key = parts[0].trim().toLowerCase();
            String value = parts[1].trim().toLowerCase();

            switch (key) {

                case "a":
                    try {
                        algorithm = Algorithm.fromSymbol(value);
                        aValid = true;
                    } catch (IllegalArgumentException e) {
                        aValid = false;
                    }
                    break;

                case "t":
                    if (value.equals("c") || value.equals("n")) {
                        t = value;
                        tValid = true;
                    } else {
                        tValid = false;
                    }
                    break;

                case "o":
                    try {
                        orientation = Orientation.fromSymbol(value);
                    } catch (IllegalArgumentException e) {
                        invalidField = "Orientation";
                        errorMessage = "Value of Orientation is invalid";
                        return false;
                    }
                    break;

                case "u":
                case "r":
                    try {
                        u = Arrays.stream(value.split(","))
                                .mapToInt(Integer::parseInt)
                                .toArray();
                        uValid = true;
                    } catch (Exception e) {
                        uValid = false;
                    }
                    break;

                case "f":
                    try {
                        f = Integer.parseInt(value);
                        fValid = f >= 5 && f <= 1000;
                    } catch (Exception e) {
                        fValid = false;
                    }
                    break;

                default:
                    invalidField = "Arguments";
                    errorMessage = "Unknown argument";
                    return false;
            }
        }

        return true;
    }

    public void printState() {
        System.out.println("Algorithm: [" + showState(aValid, algorithm != null ? algorithm.getLongName() : "") + "]");
        System.out.println("Type: [" + showState(tValid, t != null ? (t.equals("c") ? "Character" : "Number") : "") + "]");
        System.out.println("Troops: [" + showState(uValid, u != null ? String.valueOf(Arrays.stream(u).sum()) : "") + "]");
        System.out.println("Battlefield: [" + showState(fValid, f + " x " + f) + "]");
    }

    private String showState(Boolean valid, String value) {
        if (valid == null) {
            return "Not present";
        }
        if (!valid) {
            return "Invalid";
        }
        return value;
    }


}

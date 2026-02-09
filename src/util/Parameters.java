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
                    } catch (IllegalArgumentException e) {
                        invalidField = "Algorithm";
                        errorMessage = "Value of Algorithm is invalid";
                        return false;
                    }

                    break;

                case "t":
                    if (!value.equals("c") && !value.equals("n")) {
                        invalidField = "Type";
                        errorMessage = "Value of Type is invalid";
                        return false;
                    }
                    t = value;
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
                        u = Arrays.stream(value.split(",")).mapToInt(Integer::parseInt).toArray();
                    } catch (NumberFormatException e) {
                        invalidField = "Troops";
                        errorMessage = "Value of Troops is invalid";
                        return false;
                    }
                    if (u.length == 0) {
                        invalidField = "Troops";
                        errorMessage = "Value of Troops is invalid";
                        return false;
                    }
                    break;

                case "f":
                    try {
                        f = Integer.parseInt(value);
                    } catch (NumberFormatException e) {
                        invalidField = "Battlefield";
                        errorMessage = "Value of Battlefield size is invalid";
                        return false;
                    }
                    if (f < 5 || f > 1000) {
                        invalidField = "Battlefield";
                        errorMessage = "Value of Battlefield size is invalid";
                        return false;
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
}

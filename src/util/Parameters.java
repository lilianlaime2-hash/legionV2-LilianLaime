package util;

import model.Algorithm;
import model.Orientation;

import java.util.Arrays;

public class Parameters {

    public Algorithm a;
    public String t;
    public Orientation o = Orientation.SOUTH;
    public int[] u;
    public int f = 10;

    public Boolean aValid;
    public Boolean tValid;
    public Boolean uValid;
    public Boolean fValid = true;
    public Boolean oValid;

    public boolean validate(String[] args) {

        if (args == null || args.length == 0) {
            aValid = null;
            tValid = null;
            uValid = null;
            fValid = null;
            oValid = null;
            return true;
        }

        for (String arg : args) {

            String[] parts = arg.split("=");
            if (parts.length != 2) {
                aValid = false;
                tValid = false;
                uValid = false;
                fValid = true;
                oValid = true;
                continue;
            }

            String key = parts[0].trim().toLowerCase();
            String value = parts[1].trim().toLowerCase();

            switch (key) {

                case "a":
                    try {
                        a = Algorithm.fromSymbol(value);
                        aValid = true;
                    } catch (Exception e) {
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
                        o = Orientation.fromSymbol(value);
                        oValid = true;
                    } catch (Exception e) {
                        oValid = false;
                    }
                    break;

                case "u":
                case "r":
                    try {
                        u = Arrays.stream(value.split(","))
                                .map(String::trim)
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
                        if (f >= 5 && f <= 1000) {
                            fValid = true;
                        } else {
                            fValid = false;
                        }
                    } catch (Exception e) {
                        fValid = false;
                    }
                    break;

            }
        }

        return true;
    }

    public void printState() {

        System.out.println("Algorithm: [" + showState(aValid, a != null ? a.getLongName() : "") + "]");
        System.out.println("Type: [" + showState(tValid, t != null ? (t.equals("c") ? "Character" : "Number") : "") + "]");
        System.out.println("Orientation: [" + showState(oValid, o != null ? o.getLongName() : "") + "]");

        int troopsCount = 0;
        if (u != null) {
            for (int x : u) troopsCount += x;
        }

        System.out.println("Troops: [" + showState(uValid, u != null ? String.valueOf(troopsCount) : "") + "]");
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
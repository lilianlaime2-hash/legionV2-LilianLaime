package service;

import util.Parameters;

import java.util.Arrays;

public class BattleFieldValidator {

    public boolean validateCapacity(Parameters parameters) {

        int totalTroops = Arrays.stream(parameters.u).sum();
        int battlefieldSize = parameters.f * parameters.f;

        if (totalTroops > battlefieldSize) {
            System.out.println();
            System.out.println("Error: \"Too many troops for battlefield size\"");
            System.out.println("Troops: " + totalTroops + "\nCapacity: " + battlefieldSize);
            return false;
        }

        int requiredLines = 0;
        for (int amount : parameters.u) {
            requiredLines += (int) Math.ceil((double) amount / parameters.f);
        }

        if (requiredLines > parameters.f) {
            System.out.println();
            System.out.println("Error: \"Troop distribution exceeds battlefield height\"");
            return false;
        }

        return true;
    }
}
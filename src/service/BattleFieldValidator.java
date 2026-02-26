package service;

import util.GameConfig;

import java.util.Arrays;

public class BattleFieldValidator {

    public boolean validateCapacity(GameConfig config) {

        int totalTroops = Arrays.stream(config.getUnits()).sum();
        int battlefieldSize = config.getFieldSize() * config.getFieldSize();

        if (totalTroops > battlefieldSize) {
            System.out.println();
            System.out.println("Error: \"Too many troops for battlefield size\"");
            System.out.println("Troops: " + totalTroops + "\nCapacity: " + battlefieldSize);
            return false;
        }

        int requiredLines = 0;
        for (int amount : config.getUnits()) {
            requiredLines += (int) Math.ceil((double) amount / config.getFieldSize());
        }

        if (requiredLines > config.getFieldSize()) {
            System.out.println();
            System.out.println("Error: \"Troop distribution exceeds battlefield height\"");
            return false;
        }

        return true;
    }
}
package validation.semantic;

import creation.model.GameConfig;

public class BattleFieldValidator {

    public boolean validateCapacity(GameConfig config) {
        return troopsFitInArea(config) && troopsFitPerLine(config);
    }

    private boolean troopsFitInArea(GameConfig config) {

        int totalTroops = config.sumUnits();
        int battlefieldArea = config.getFieldSize() * config.getFieldSize();

        if (totalTroops <= 0) {
            System.out.println();
            System.out.println("Error: \"Troops must be greater than 0\"");
            return false;
        }

        if (totalTroops > battlefieldArea) {
            System.out.println();
            System.out.println("Error: \"Too many troops for battlefield size\"");
            System.out.println("Troops: " + totalTroops + "\nCapacity: " + battlefieldArea);
            return false;
        }

        return true;
    }

    private boolean troopsFitPerLine(GameConfig config) {
        int requiredLines = 0;
        for (int troopAmount : config.getUnits()) {
            int linesPerTroop = (int) Math.ceil((double) troopAmount / config.getFieldSize());
            requiredLines += linesPerTroop;
        }

        if (requiredLines > config.getFieldSize()) {
            System.out.println();
            System.out.println("Error: \"Each troop type needs separate lines, but battlefield height is not enough\"");
            return false;
        }

        return true;
    }
}

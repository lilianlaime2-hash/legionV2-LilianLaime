package controller;

import model.*;
import model.Character;
import util.Parameters;

import java.util.*;

public class GameController {

    private final Parameters parameters = new Parameters();
    private final Random random = new Random();
    int totalTroops = 0;

    public void startGame(String[] args) {

        parameters.validate(args);
        parameters.printState();

        if (Boolean.FALSE.equals(parameters.aValid) || Boolean.FALSE.equals(parameters.tValid) || Boolean.FALSE.equals(parameters.uValid) || Boolean.FALSE.equals(parameters.fValid)) {

            System.out.println("Error: \"Invalid arguments\"");
            return;
        }

        if (parameters.a == null || parameters.t == null || parameters.u == null) {
            return;
        }

        int battlefieldSize = parameters.f * parameters.f;

        for (int amount : parameters.u) {
            totalTroops += amount;
        }

        if (totalTroops > battlefieldSize) {
            System.out.println();
            System.out.println("Error: \"Too many troops for battlefield size\"");
            System.out.println("Troops: " + totalTroops + "\nCapacity: " + battlefieldSize);
            return;
        }

        int requiredLines = 0;
        for (int amount : parameters.u) {
            requiredLines += (int) Math.ceil((double) amount / parameters.f);
        }

        if (requiredLines > parameters.f) {
            System.out.println();
            System.out.println("Error: \"Troop distribution exceeds battlefield height\"");
            return;
        }


        List<Character> troops = buildTroops();

        BattleField battleField = new BattleField(parameters.f, parameters.f);
        placeRandom(battleField, troops);

        System.out.println("\nInitial Position:");
        battleField.showBattleField(parameters.t);

        System.out.println("\nFinal Position:");
        battleField.sortMatrix(comparator(), parameters.o);
        battleField.showBattleField(parameters.t);
    }

    private List<Character> buildTroops() {

        List<Character> troops = new ArrayList<>();

        TroopType[] types = TroopType.values();

        for (int i = 0; i < parameters.u.length && i < types.length; i++) {

            TroopType type = types[i];
            int amount = parameters.u[i];

            for (int j = 0; j < amount; j++) {

                int value = type.getMin() + j;

                troops.add(createCharacter(type, value));
            }
        }

        return troops;
    }

    private Character createCharacter(TroopType type, int value) {

        switch (type) {
            case COMMANDER:
                return new Commander(value);
            case MEDIC:
                return new Medic(value);
            case TANK:
                return new Tank(value);
            case SNIPER:
                return new Sniper(value);
            case INFANTRY:
                return new Infantry(value);
            default:
                throw new IllegalArgumentException("Unknown type");
        }
    }

    private Comparator<Character> comparator() {
        return new Comparator<Character>() {
            @Override
            public int compare(Character a, Character b) {
                if (parameters.t.equals("c")) {
                    return a.getType().compareTo(b.getType());
                } else {
                    return a.getValue() - b.getValue();
                }
            }
        };
    }

    private void placeRandom(BattleField battleField, List<Character> troops) {
        for (Character troop : troops) {
            int row, col;
            do {
                row = random.nextInt(parameters.f);
                col = random.nextInt(parameters.f);
            } while (!battleField.getCell(row, col).isEmpty());

            battleField.placeCharacter(troop, row, col);
        }
    }

}
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
            System.out.println("Error: \"invalid battlefield size\"");
            return;
        }

        int requiredLines = 0;
        for (int amount : parameters.u) {
            requiredLines += (int) Math.ceil((double) amount / parameters.f);
        }

        if (requiredLines > parameters.f) {
            System.out.println();
            System.out.println("Error: \"invalid battlefield size\"");
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

        if (parameters.u.length > 0)
            for (int i = 0; i < parameters.u[0]; i++) troops.add(new Commander());

        if (parameters.u.length > 1)
            for (int i = 0; i < parameters.u[1]; i++) troops.add(new Medic());

        if (parameters.u.length > 2)
            for (int i = 0; i < parameters.u[2]; i++) troops.add(new Tank());

        if (parameters.u.length > 3)
            for (int i = 0; i < parameters.u[3]; i++) troops.add(new Sniper());

        if (parameters.u.length > 4)
            for (int i = 0; i < parameters.u[4]; i++) troops.add(new Infantry());

        return troops;
    }

    private Comparator<Character> comparator() {
        return new Comparator<Character>() {
            @Override
            public int compare(Character a, Character b) {
                if (parameters.t.equals("c")) {
                    return a.getType().compareTo(b.getType());
                } else {
                    return a.getType().getValue() - b.getType().getValue();
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
package controller;

import model.*;
import model.Character;
import sort.InsertionSort;
import sort.SortStrategy;
import util.Parameters;

import java.util.*;

public class GameController {


    private final Parameters parameters = new Parameters();
    private final Random random = new Random();
    int totalTroops = 0;

    public void startGame(String[] args) {


        if (!parameters.validate(args)) {
            System.out.println("Invalid arguments");
            return;
        }

        printArguments();
        int battlefieldSize = parameters.f * parameters.f;


        for (int amount : parameters.u){
            totalTroops += amount;
        }
        if (totalTroops > battlefieldSize) {
            System.out.println("Error: \"invalid battlefield size\"");
            return;
        }

        List<Character> troops = buildTroops();
        //System.out.println("\n-----Troops-----");
        //System.out.println(troops);


        BattleField battleField = new BattleField(parameters.f, parameters.f);
        placeRandom(battleField, troops);

        System.out.println("\nInitial Position:");
        battleField.showBattleField();


        System.out.println("\nFinal Position:");
        battleField.sortMatrix(comparator(), parameters.orientation);
        battleField.showBattleField();


        parameters.validate(args);
        parameters.printState();

        if (Boolean.FALSE.equals(parameters.aValid)
                || Boolean.FALSE.equals(parameters.tValid)
                || Boolean.FALSE.equals(parameters.uValid)
                || Boolean.FALSE.equals(parameters.fValid)) {

            System.out.println("\nInvalid values.");
            return;
        }

    }

    private List<Character> buildTroops() {
        List<Character> troops = new ArrayList<>();

        for (int i = 0; i < parameters.u[0]; i++) {
            troops.add(new Commander());
        }
        for (int i = 0; i < parameters.u[1]; i++) {
            troops.add(new Medic());
        }
        for (int i = 0; i < parameters.u[2]; i++) {
            troops.add(new Tank());
        }
        for (int i = 0; i < parameters.u[3]; i++) {
            troops.add(new Sniper());
        }
        for (int i = 0; i < parameters.u[4]; i++) {
            troops.add(new Infantry());
        }

        return troops;
    }

    private void sort(List<Character> troops) {
        SortStrategy<Character> sorter = new InsertionSort<>();
        sorter.sort(troops, comparator());
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

    public void printArguments() {


        System.out.println("\nArguments: ");
        System.out.println("Algorithm = " + "[" +  parameters.algorithm.getLongName() + "]");
        String typeName = parameters.t.equals("c") ? "[Character]": "[Number]";
        System.out.println("Type = "  + typeName );
        System.out.println("Orientation = " + "["+ parameters.orientation.getLongName() + "]");
        System.out.println("Troops = [" + totalTroops + "]");
        System.out.println("Battle field = [" + parameters.f + " x " + parameters.f + "]");


    }



}

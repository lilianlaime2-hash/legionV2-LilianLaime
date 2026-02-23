package controller;

import model.*;
import model.Character;
import service.*;
import sorting.*;
import view.BattleFieldView;
import util.Parameters;
import sorting.ComparatorFactory;

import java.util.*;

public class GameController {

    private final Parameters parameters = new Parameters();
    private final Random random = new Random();

    private final BattleFieldService battleFieldService = new BattleFieldService();
    private final ComparatorFactory comparatorFactory = new ComparatorFactory();
    private final BattleFieldView battleFieldView = new BattleFieldView();

    public void startGame(String[] args) {

        parameters.validate(args);
        parameters.printState();

        if (invalidParameters()) {
            System.out.println("Error: \"Invalid arguments\"");
            return;
        }

        if (!validateBattlefieldCapacity()) {
            return;
        }

        List<Character> troops = buildTroops();

        BattleField battleField = new BattleField(parameters.f);

        placeRandom(battleField, troops);

        System.out.println("\nInitial Position:");
        battleFieldView.print(battleField, parameters.t);

        SortStrategy<Character> strategy = resolveStrategy(parameters.a);

        Comparator<Character> comparator = comparatorFactory.create(parameters.t);

        SortingContext context = new SortingContext(strategy, comparator, parameters.o);

        battleFieldService.sort(battleField, context);

        System.out.println("\nFinal Position:");
        battleFieldView.print(battleField, parameters.t);
    }

    private boolean invalidParameters() {
        return Boolean.FALSE.equals(parameters.aValid)
                || Boolean.FALSE.equals(parameters.tValid)
                || Boolean.FALSE.equals(parameters.uValid)
                || Boolean.FALSE.equals(parameters.fValid)
                || parameters.a == null
                || parameters.t == null
                || parameters.u == null;
    }

    private boolean validateBattlefieldCapacity() {

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

        return switch (type) {
            case COMMANDER -> new Commander(value);
            case MEDIC -> new Medic(value);
            case TANK -> new Tank(value);
            case SNIPER -> new Sniper(value);
            case INFANTRY -> new Infantry(value);
        };
    }

    private SortStrategy<Character> resolveStrategy(Algorithm algorithm) {

        return switch (algorithm) {
            case INSERTION_SORT -> new InsertionSort<>();
            case BUBBLE_SORT -> new BubbleSort<>();
            case MERGE_SORT -> new MergeSort<>();
            case QUICK_SORT -> new QuickSort<>();
        };
    }

    private void placeRandom(BattleField battleField, List<Character> troops) {

        for (Character troop : troops) {

            int row;
            int col;

            do {
                row = random.nextInt(parameters.f);
                col = random.nextInt(parameters.f);
            } while (!battleField.getCell(row, col).isEmpty());

            battleField.placeCharacter(troop, row, col);
        }
    }
}
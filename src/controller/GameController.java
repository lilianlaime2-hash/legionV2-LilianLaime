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
    private final BattleFieldValidator battleFieldValidator = new BattleFieldValidator();
    private final TroopFactory troopFactory = new TroopFactory();
    private final SortStrategyFactory strategyFactory = new SortStrategyFactory();
    private final TroopPlacer troopPlacer = new TroopPlacer();

    public void startGame(String[] args) {

        parameters.validate(args);
        parameters.printState();

        if (invalidParameters()) {
            System.out.println("Error: \"Invalid arguments\"");
            return;
        }

        if (!battleFieldValidator.validateCapacity(parameters)) {
            return;
        }

        List<Character> troops = troopFactory.buildTroops(parameters.u);

        BattleField battleField = new BattleField(parameters.f);

        troopPlacer.placeRandom(battleField, troops, parameters.f);

        System.out.println("\nInitial Position:");
        battleFieldView.print(battleField, parameters.t);

        SortStrategy<Character> strategy = strategyFactory.resolve(parameters.a);

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

}
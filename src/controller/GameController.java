package controller;

import model.BattleField;
import model.Character;
import service.BattleFieldService;
import service.BattleFieldValidator;
import service.TroopFactory;
import service.TroopPlacer;
import sorting.ComparatorFactory;
import sorting.SortStrategy;
import sorting.SortStrategyFactory;
import sorting.SortingContext;
import util.CliParser;
import util.GameConfig;
import util.ParseReport;
import view.BattleFieldView;

import java.util.Comparator;
import java.util.List;

public class GameController {

    private final CliParser cliParser = new CliParser();
    private final BattleFieldValidator validator = new BattleFieldValidator();
    private final TroopFactory troopFactory = new TroopFactory();
    private final TroopPlacer troopPlacer = new TroopPlacer();
    private final SortStrategyFactory strategyFactory = new SortStrategyFactory();
    private final ComparatorFactory comparatorFactory = new ComparatorFactory();
    private final BattleFieldService battleFieldService = new BattleFieldService();
    private final BattleFieldView view = new BattleFieldView();

    public void startGame(String[] args) {

        ParseReport report = cliParser.parse(args);

        report.printState();

        if (report.hasFatalErrors()) {
            System.out.println("Error: \"Invalid arguments\"");
            return;
        }

        GameConfig config = report.getConfig();

        if (!validator.validateCapacity(config)) return;

        List<Character> troops = troopFactory.buildTroops(config.getUnits());

        BattleField battleField = new BattleField(config.getFieldSize());

        troopPlacer.placeRandom(battleField, troops, config.getFieldSize());

        System.out.println("\nInitial Position:");
        view.print(battleField, config.getType());

        SortStrategy<Character> strategy =
                strategyFactory.resolve(config.getAlgorithm());

        Comparator<Character> comparator =
                comparatorFactory.create(config.getType());

        SortingContext<Character> context =
                new SortingContext<>(strategy, comparator, config.getOrientation());

        battleFieldService.sort(battleField, context);

        System.out.println("\nFinal Position:");
        view.print(battleField, config.getType());
    }
}
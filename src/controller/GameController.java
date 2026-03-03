package controller;

import creation.model.BattleField;
import creation.model.GameConfig;
import service.GameEngine;
import validation.semantic.BattleFieldValidator;
import validation.syntax.CliParser;
import validation.syntax.ParseReport;
import view.BattleFieldView;

public class GameController {

    private final CliParser cliParser;
    private final BattleFieldValidator validator;
    private final GameEngine gameEngine;
    private final BattleFieldView view;

    public GameController(
            CliParser cliParser,
            BattleFieldValidator validator,
            GameEngine gameEngine,
            BattleFieldView view) {

        this.cliParser = cliParser;
        this.validator = validator;
        this.gameEngine = gameEngine;
        this.view = view;
    }

    public void startGame(String[] args) {

        ParseReport report = cliParser.parse(args);

        report.printState();

        if (report.hasErrors()) {
            System.out.println("Error: \"Invalid arguments\"");
            return;
        }

        GameConfig config = report.getConfig();

        if (!validator.validateCapacity(config)) return;

        BattleField battleField = gameEngine.createBattleField(config);

        System.out.println("\nInitial Position:");
        view.print(battleField, config.getType());

        double sortingSeconds = gameEngine.sortBattleField(battleField, config);

        System.out.println("\nFinal Position:");
        view.print(battleField, config.getType());
        System.out.printf("Sorting time: %.3f s", sortingSeconds);
    }
}

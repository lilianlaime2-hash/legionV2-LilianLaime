import controller.GameController;
import creation.TroopFactory;
import creation.TroopPlacer;
import service.GameEngine;
import sorting.BattleFieldService;
import sorting.SortStrategyFactory;
import validation.semantic.BattleFieldValidator;
import validation.syntax.CliParser;
import view.BattleFieldView;

public class Main {

    public static void main(String[] args) {

        TroopPlacer troopPlacer = new TroopPlacer();

        GameEngine engine = new GameEngine(
                new TroopFactory(),
                troopPlacer,
                new SortStrategyFactory(),
                new BattleFieldService(troopPlacer)
        );

        GameController controller = new GameController(
                new CliParser(),
                new BattleFieldValidator(),
                engine,
                new BattleFieldView()
        );

        controller.startGame(args);
    }
}

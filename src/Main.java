import controller.GameController;
import service.*;
import sorting.*;
import util.CliParser;
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

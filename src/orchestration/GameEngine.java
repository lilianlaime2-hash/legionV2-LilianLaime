package orchestration;

import creation.TroopFactory;
import creation.TroopPlacer;
import creation.model.BattleField;
import creation.model.character.Character;
import creation.model.GameConfig;
import sorting.CharacterComparator;
import sorting.SortStrategy;
import sorting.SortStrategyFactory;
import sorting.SortingContext;

import java.util.Comparator;
import java.util.List;

public class GameEngine {

    private final TroopFactory troopFactory;
    private final TroopPlacer troopPlacer;
    private final SortStrategyFactory strategyFactory;
    private final BattleFieldService battleFieldService;

    public GameEngine(
            TroopFactory troopFactory,
            TroopPlacer troopPlacer,
            SortStrategyFactory strategyFactory,
            BattleFieldService battleFieldService) {

        this.troopFactory = troopFactory;
        this.troopPlacer = troopPlacer;
        this.strategyFactory = strategyFactory;
        this.battleFieldService = battleFieldService;
    }

    public BattleField createBattleField(GameConfig config) {

        List<Character> troops = troopFactory.buildTroops(config.getUnits());

        BattleField battleField = new BattleField(config.getFieldSize());

        troopPlacer.placeRandom(
                battleField,
                troops,
                config.getFieldSize());

        return battleField;
    }

    public double sortBattleField(BattleField battleField, GameConfig config) {
        SortingContext context = prepareSortingContext(config);

        long startTime = System.nanoTime();
        battleFieldService.sort(battleField, context, config.getType());
        long endTime = System.nanoTime();

        return (endTime - startTime) / 1_000_000_000.0;
    }

    private SortingContext prepareSortingContext(GameConfig config) {
        SortStrategy strategy = strategyFactory.createSortStrategy(config.getAlgorithm());
        Comparator<Character> comparator = new CharacterComparator();

        return new SortingContext(strategy, comparator, config.getOrientation()
        );
    }
}

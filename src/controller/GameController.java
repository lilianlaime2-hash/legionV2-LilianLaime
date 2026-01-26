package controller;

import model.*;
import sort.*;
import util.*;
import util.Validator;
import view.ConsoleView;

public class GameController {

    public void startGame(String[] args) {

        ArgumentParser parser = new ArgumentParser();
        Validator validator = new Validator();
        ConsoleView view = new ConsoleView();

        if (!validator.validateParameter(args)) {
            view.showError("Invalid parameters");
            return;
        }

        BattleField battlefield = new BattleField(10);

        battlefield.placeTroop(new Commanders());
        battlefield.placeTroop(new Medics());
        battlefield.placeTroop(new Tanks());
        battlefield.placeTroop(new Sniper());
        battlefield.placeTroop(new Infantry());

        view.showInitialBattlefield(battlefield);

        SortController sorter = new SortController();
        sorter.setStrategy(new QuickSort());
        sorter.executeSort(battlefield.getTroops());

        view.showFinalBattlefield(battlefield);
    }
}

package view;

import model.BattleField;

public class ConsoleView {

    public void showInitialBattlefield(BattleField battlefield) {
        System.out.println("Initial Battlefield");
    }

    public void showFinalBattlefield(BattleField battlefield) {
        System.out.println("Final Battlefield");
    }

    public void showError(String msg) {
        System.out.println("Error: " + msg);
    }
}

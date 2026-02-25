package service;

import model.*;
import model.Character;

import java.util.ArrayList;
import java.util.List;

public class TroopFactory {

    public List<Character> buildTroops(int[] units) {

        List<Character> troops = new ArrayList<>();
        TroopType[] types = TroopType.values();

        for (int i = 0; i < units.length && i < types.length; i++) {

            TroopType type = types[i];
            int amount = units[i];

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
}
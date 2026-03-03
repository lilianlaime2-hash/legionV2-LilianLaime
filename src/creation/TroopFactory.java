package creation;

import creation.model.character.Character;
import creation.model.character.Commander;
import creation.model.character.Infantry;
import creation.model.character.Medic;
import creation.model.character.Sniper;
import creation.model.character.Tank;
import creation.model.character.TroopType;

import java.util.ArrayList;
import java.util.List;

public class TroopFactory {

    public List<Character> buildTroops(int[] units) {

        List<Character> troops = new ArrayList<>();
        TroopType[] types = TroopType.values();

        for (int i = 0; i < units.length; i++) {

            TroopType type = types[i];
            int amount = units[i];

            for (int j = 0; j < amount; j++) {
                int value = type.getMin() + j; //
                troops.add(create(type, value));
            }
        }

        return troops;
    }

    private Character create(TroopType type, int value) {
        return switch (type) {
            case COMMANDER -> new Commander(value);
            case MEDIC -> new Medic(value);
            case TANK -> new Tank(value);
            case SNIPER -> new Sniper(value);
            case INFANTRY -> new Infantry(value);
        };
    }
}

package sorting;

import model.Character;
import java.util.Comparator;

public class ComparatorFactory {

    public Comparator<Character> create(String type) {

        if ("c".equals(type)) {
            return Comparator.comparing(Character::getType);
        }

        return Comparator.comparingInt(Character::getValue);
    }
}
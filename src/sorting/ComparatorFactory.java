package sorting;

import model.Character;
import model.TroopType;

import java.util.Comparator;

public class ComparatorFactory {

    public Comparator<Character> create(String type) {

        // Primero por "grupo" (tipo) para que queden contiguos y fillSorted funcione como en el original
        Comparator<Character> byTroopGroup =
                Comparator.comparingInt(c -> c.getType().getMin()); // o usa ordinal()

        // Luego por valor dentro del mismo tipo
        Comparator<Character> byValue =
                Comparator.comparingInt(Character::getValue);

        return byTroopGroup.thenComparing(byValue);
    }
}
package sorting;

import model.Character;

import java.util.Comparator;

public class CharacterComparator implements Comparator<Character> {
    private static final int BEFORE = -1;
    private static final int EQUAL = 0;
    private static final int AFTER = 1;

    @Override
    public int compare(Character characterA, Character characterB) {
        int relativePosition = compareByCharacterType(characterA, characterB);

        boolean sameType = (relativePosition == EQUAL);
        if (sameType) {
            return compareByValueAsTieBreaker(characterA, characterB);
        }

        return relativePosition;
    }

    private int compareByCharacterType(Character characterA, Character characterB) {
        int typeOfA = characterA.getType().getMin();
        int typeOfB = characterB.getType().getMin();

        if (typeOfA < typeOfB) return BEFORE;
        if (typeOfA > typeOfB) return AFTER;
        return EQUAL;
    }

    private int compareByValueAsTieBreaker(Character characterA, Character characterB) {
        int valueOfA = characterA.getValue();
        int valueOfB = characterB.getValue();

        if (valueOfA < valueOfB) return BEFORE;
        if (valueOfA > valueOfB) return AFTER;
        return EQUAL;
    }
}

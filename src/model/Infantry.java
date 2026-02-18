package model;

public class Infantry extends Character implements Shootable {

    public Infantry(int value) {
        super(TroopType.INFANTRY, value);
    }
}

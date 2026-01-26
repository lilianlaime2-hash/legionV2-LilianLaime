package model;

public class Commanders extends Character {

    public Commanders() {
        health = 120;
        speed = 3;
        rank = 10;
    }

    public void giveOrder() {
        System.out.println("Commander gives orders");
    }

    @Override
    public void act() {
        giveOrder();
    }
}

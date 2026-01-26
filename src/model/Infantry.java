package model;

public class Infantry extends Character implements Shootable {

    public Infantry() {
        health = 100;
        speed = 5;
        rank = 50;
    }

    @Override
    public void attack() {
        System.out.println("Infantry attacks");
    }

    @Override
    public void act() {
        attack();
    }
}

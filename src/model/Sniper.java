package model;

public class Sniper extends Character implements Shootable {

    public Sniper() {
        health = 80;
        speed = 4;
        rank = 40;
    }

    @Override
    public void attack() {
        System.out.println("Sniper shoots");
    }

    @Override
    public void act() {
        attack();
    }
}

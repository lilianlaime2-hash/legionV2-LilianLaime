package model;

public class Tanks extends Character implements Shootable {

    public Tanks() {
        health = 200;
        speed = 2;
        rank = 30;
    }

    @Override
    public void attack() {
        System.out.println("Tank fires");
    }

    @Override
    public void act() {
        attack();
    }
}

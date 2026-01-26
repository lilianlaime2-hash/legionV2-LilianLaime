package model;

public class Medics extends Character {

    public Medics() {
        health = 90;
        speed = 4;
        rank = 20;
    }

    public void heal(Character ally) {
        ally.health += 15;
    }

    @Override
    public void act() {
        System.out.println("Medic heals");
    }
}

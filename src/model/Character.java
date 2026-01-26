package model;

public abstract class Character {

    protected int health;
    protected int speed;
    protected int rank;
    protected int row;
    protected int column;

    public abstract void act();

    public void move(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public void takeDamage(int damage) {
        health = Math.max(0, health - damage);
    }

    public boolean isAlive() {
        return health > 0;
    }

    public int getRank() {
        return rank;
    }
}

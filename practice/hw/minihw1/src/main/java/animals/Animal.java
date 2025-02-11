package animals;

import interfaces.IAlive;

public class Animal implements IAlive {

    private int health;
    private int food;
    private String name;

    public int getHealth() {
        return health;
    }

    public Animal(int health, int food, String name) {
        this.health = health;
        this.food = food;
        this.name = name;
    }

    @Override
    public int getFood() {
        return food;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean isContact() {
        return false;
    }

    void feed() {}
}

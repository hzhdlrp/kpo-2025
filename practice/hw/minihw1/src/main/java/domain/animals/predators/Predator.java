package domain.animals.predators;

import domain.animals.Animal;

public class Predator extends Animal {
    public Predator(int health, int food, String name, String nickname, String sex, String favoriteFood) {
        super(health, food, name, nickname, sex, favoriteFood, 0);
    }
}

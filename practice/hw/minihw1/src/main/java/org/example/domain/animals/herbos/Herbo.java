package org.example.domain.animals.herbos;

import org.example.domain.animals.Animal;

public class Herbo extends Animal {

    protected int kindness;

    int getKindness() {
        return kindness;
    }

    public Herbo(int health, int food, String name, String nickname, String sex,
                 String favoriteFood, int kindness) {
        super(health, food, name, nickname, sex, favoriteFood);
        if (kindness < 0 || kindness > 10) {
            throw new RuntimeException("kindness can be only between 0 and 10\n");
        }
        this.kindness = kindness;
    }

    @Override
    public boolean isContact() {
        return (kindness >= 5);
    }
}

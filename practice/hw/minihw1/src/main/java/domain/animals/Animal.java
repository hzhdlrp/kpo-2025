package domain.animals;

import domain.interfaces.IAlive;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

public class Animal implements IAlive {

    private int health;
    private int food;
    private String name;
    @Getter
    private LocalDate birthday;
    @Getter
    private String sex;
    @Getter
    @Setter
    private String status;
    @Getter
    private String favoriteFood;
    @Getter
    private String nickname; // кличка
    @Getter
    @Setter
    private int enclosureId;

    public int getHealth() {
        return health;
    }

    public Animal(int health, int food, String name, String nickname,
                  String sex, String favoriteFood, int enclosureId) {
        this.health = health;
        this.food = food;
        this.name = name;
        this.birthday = LocalDate.now();
        this.sex = sex;
        this.status = "healthy";
        this.favoriteFood = favoriteFood;
        this.nickname = nickname;
        this.enclosureId = enclosureId;
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

    public int feed() {
        this.food++;
        return this.food;
    }

    public int heal() {
        this.health++;
        return this.health;
    }
}

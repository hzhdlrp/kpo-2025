package domain.animals;

import domain.animals.herbos.Herbo;
import domain.animals.herbos.Monkey;
import domain.feeding.FeedingSchedule;
import domain.feeding.FoodTypes;
import domain.interfaces.IAlive;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalTime;

@ToString
public class Animal implements IAlive {

    private int health;
    @Getter
    @Setter
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
    private FeedingSchedule feedingSchedule;

    public int getHealth() {
        return health;
    }

    public Animal(int health, int food, String name, String nickname,
                  String sex, String favoriteFood) {
        if (!sex.equals("male") && !sex.equals("female")) {
            throw new RuntimeException("error, sex can only be male or female");
        }
        this.health = health;
        this.food = food;
        this.name = name;
        this.birthday = LocalDate.now();
        this.sex = sex;
        this.status = "healthy";
        this.favoriteFood = favoriteFood;
        this.nickname = nickname;
        FoodTypes type;
        if (this instanceof Monkey) type = FoodTypes.FRUITS;
        else if (this instanceof Herbo) type = FoodTypes.GRASS;
        else type = FoodTypes.MEAT;
        this.feedingSchedule = FeedingSchedule.builder()
                .feedingTime(LocalTime.now())
                .type(type)
                .fed(false)
                .build();
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
        this.feedingSchedule.setFed(true);
        return this.food;
    }

    public int heal() {
        this.health++;
        return this.health;
    }
}

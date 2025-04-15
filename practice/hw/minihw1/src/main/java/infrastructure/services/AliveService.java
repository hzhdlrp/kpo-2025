package infrastructure.services;

import domain.animals.Animal;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class AliveService {

    @Getter
    private List<Animal> animals = new ArrayList<>();
    private List<Animal> contactAnimals = new ArrayList<>();
    private Set<String> nicknames = new HashSet<>();

    public void addAnimal(Animal animal) {
        if (nicknames.contains(animal.getNickname())) {
            System.out.println("error, animal with this nickname already exists");
            return;
        }
        nicknames.add(animal.getNickname());
        animals.add(animal);

        if (animal.isContact()) {
            contactAnimals.add(animal);
        }
    }

    public Animal getAnimalByNickname(String nickname) {
        for (Animal animal : animals) {
            if (animal.getNickname().equals(nickname)) {
                return animal;
            }
        }
        System.out.println("no animal with nickname " + nickname);
        return null;
    }


    public void printAnimalsInfo() {
        animals.forEach(animal -> {
            System.out.print(animal.getName());
            System.out.print("\tfood: ");
            System.out.println(animal.getFood());
        });
    }

    public void printAnimalsNumber() {
        System.out.println(animals.size());
    }

    public void printAllFoodCount() {
        AtomicInteger foodCount = new AtomicInteger();
        animals.forEach(animal -> {
            foodCount.addAndGet(animal.getFood());
        });
        System.out.println(foodCount);
    }

    public void printContactList() {
        contactAnimals.forEach(animal -> {
            System.out.println(animal.getName());
        });
    }
}

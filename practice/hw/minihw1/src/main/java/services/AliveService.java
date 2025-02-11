package services;

import animals.Animal;
import interfaces.IAlive;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class AliveService {

    private List<IAlive> animals = new ArrayList<>();
    private List<IAlive> contactAnimals = new ArrayList<>();

    public void addAnimal(Animal animal) {
        animals.add(animal);

        if (animal.isContact()) {
            contactAnimals.add(animal);
        }
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

package services;

import animals.Animal;
import interfaces.IAlive;
import org.springframework.stereotype.Component;
import things.Thing;
import veterinary.VetClinic;

import java.util.concurrent.atomic.AtomicInteger;

@Component
public class Zoo {

    public void setClinic(VetClinic clinic) {
        this.clinic = clinic;
    }

    public Zoo(int criteria) {
        this.clinic = new VetClinic(criteria);
    }

    public void addAnimal(Animal animal) {
        if (clinic.check(animal)) {
            animalService.addAnimal(animal);
        }
    }

    public void addThing(Thing thing) {
        thingService.addThing(thing);
    }

    public void printAnimalsInfo() {
        animalService.printAnimalsInfo();
    }

    public void printAnimalsNumber() {
        animalService.printAnimalsNumber();
    }

    public void printAllFoodCount() {
        animalService.printAllFoodCount();
    }

    public void printContactList() {
        animalService.printContactList();
    }

    public void printThingsInfo() {
        thingService.printThingsInfo();
    }

    private VetClinic clinic;
    private AliveService animalService = new AliveService();
    private ThingService thingService = new ThingService();
}

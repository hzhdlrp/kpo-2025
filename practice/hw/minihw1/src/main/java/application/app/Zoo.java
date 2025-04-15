package application.app;

import infrastructure.services.AliveService;
import infrastructure.services.ThingService;
import domain.animals.Animal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import domain.things.Thing;
import application.veterinary.VetClinic;

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
    @Autowired
    private AliveService animalService;
    @Autowired
    private ThingService thingService;
}

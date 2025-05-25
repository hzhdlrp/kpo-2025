package org.example.application.zoo;

import org.example.infrastructure.services.AliveService;
import org.example.infrastructure.services.ThingService;
import org.example.domain.animals.Animal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.example.domain.things.Thing;
import org.example.application.veterinary.VetClinic;

@Component
public class Zoo {

    public int addAnimal(Animal animal) {
        if (clinic.check(animal)) {
            if (animalService.addAnimal(animal))
                return 0;
            return -1;
        }
        return -2;
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
    private AliveService animalService;
    private ThingService thingService;

    @Autowired
    public Zoo(VetClinic clinic, AliveService animalService, ThingService thingService) {
        this.clinic = clinic;
        this.animalService = animalService;
        this.thingService = thingService;
    }
}

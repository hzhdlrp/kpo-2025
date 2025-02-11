package services;

import animals.Animal;
import interfaces.IInventory;
import org.springframework.stereotype.Component;
import things.Thing;

import java.util.ArrayList;
import java.util.List;

@Component
public class ThingService {
    private List<IInventory> things = new ArrayList<>();

    private int number = 0;

    public void addThing(Thing thing) {
        thing.setNumver(this.number);
        this.number++;
        things.add(thing);
    }

    public void printThingsInfo() {
        things.forEach(thing -> {
            System.out.print(thing.getName());
            System.out.print("\tnumber: ");
            System.out.println(thing.getNumber());
        });
    }
}

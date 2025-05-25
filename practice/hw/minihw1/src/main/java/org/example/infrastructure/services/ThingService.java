package org.example.infrastructure.services;

import org.example.domain.interfaces.IInventory;
import org.example.domain.things.Thing;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ThingService {
    private List<IInventory> things = new ArrayList<>();

    private int number = 0;

    public int addThing(Thing thing) {
        thing.setNumver(this.number);
        this.number++;
        things.add(thing);
        return number;
    }

    public void printThingsInfo() {
        things.forEach(thing -> {
            System.out.print(thing.getName());
            System.out.print("\tnumber: ");
            System.out.println(thing.getNumber());
        });
    }
}

package org.example.domain.things;

import org.example.domain.interfaces.IInventory;

public class Thing implements IInventory {

    private int number;
    private String name;

    public Thing(String name) {
        this.name = name;
    }

    @Override
    public int getNumber() {
        return number;
    }

    @Override
    public void setNumver(int number) {
        this.number = number;
    }

    @Override
    public String getName() {
        return name;
    }
}

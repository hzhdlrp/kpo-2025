package org.example.application.veterinary;

import org.example.domain.animals.Animal;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class VetClinic {

    private int criteria;

    public VetClinic(@Value("${zoo.health.criteria:20}") int criteria) {
        this.criteria = criteria;
    }

    public void setCriteria(int crit) {
        this.criteria = crit;
    }

    public boolean check(Animal animal) {
        if (animal.getHealth() < criteria) {
            System.out.print(String.format("cannot add to zoo, too low health, criteria = %d\n" , criteria));
            return false;
        } else {
            return true;
        }
    }

}

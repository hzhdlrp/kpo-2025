package infrastructure.services;

import domain.animals.herbos.Herbo;
import domain.animals.predators.Predator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ZooStatisticsService {
    @Autowired
    private AliveService aliveService;

    public int getAnimalsNumber() {
        return aliveService.getAnimals().size();
    }

    public List<String> getHerbosNames() {
        List<String> list = new ArrayList<>();
        aliveService.getAnimals().forEach(animal -> {
            if (animal instanceof Herbo) {
                list.add(animal.getName() + " " + animal.getNickname());
            }
        });
        return list;
    }

    public List<String> getPredatorsNames() {
        List<String> list = new ArrayList<>();
        aliveService.getAnimals().forEach(animal -> {
            if (animal instanceof Predator) {
                list.add(animal.getName() + " " + animal.getNickname());
            }
        });
        return list;
    }

    public List<String> getAllAnimals() {
        List<String> list = new ArrayList<>();
        aliveService.getAnimals().forEach(animal -> {
            list.add(animal.toString());
        });
        return list;
    }
}

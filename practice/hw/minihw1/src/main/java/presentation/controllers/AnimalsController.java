package presentation.controllers;

import application.app.Zoo;
import domain.animals.Animal;
import domain.animals.herbos.Monkey;
import domain.animals.herbos.Rabbit;
import domain.animals.predators.Tiger;
import domain.animals.predators.Wolf;
import domain.things.Computer;
import domain.things.Table;
import domain.things.Thing;
import infrastructure.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

class AnimalRequest {
    public String type;
    public int health;
    public String nickname;
    public String sex;
    public String favoriteFood;
}


@RestController
@RequestMapping("/api/animals")
public class AnimalsController {
    @Autowired
    private AliveService aliveService;
    @Autowired
    private ZooStatisticsService zooStatisticsService;
    @Autowired
    private AnimalTransferService animalTransferService;
    @Autowired
    FeedingAndHealingOrganizationService feedingAndHealingOrganizationService;
    @Autowired
    private ThingService thingService;
    private Zoo zoo;

    AnimalsController(Zoo zoo) {
        this.zoo = zoo;
    }

    @PostMapping("/animal")
    public ResponseEntity<String> createAnimal(@RequestBody AnimalRequest request) {
        try {
            Animal createdAnimal = _createAnimal(request.type, request.health, request.nickname, request.sex, request.favoriteFood);
            return ResponseEntity.ok("Created! new animal:\n" + createdAnimal.toString());
        } catch (RuntimeException e) {
            System.out.println(e.getCause());
            return ResponseEntity.status(666).body(e.getCause().toString());
        }
    }

    @PostMapping("/feed/{nickname}")
    public ResponseEntity<String> feedAnimal(@PathVariable String nickname) {
        String str = feedingAndHealingOrganizationService.feedByNickname(nickname);
        return ResponseEntity.ok(str);
    }

    @PostMapping("/heal/{nickname}")
    public ResponseEntity<String> healAnimal(@PathVariable String nickname) {
        String str = feedingAndHealingOrganizationService.healByNickname(nickname);
        return ResponseEntity.ok(str);
    }

    @PostMapping("/move/{nickname}")
    public ResponseEntity<String> (@RequestBody AnimalRequest request) {
        try {
            Animal createdAnimal = _createAnimal(request.type, request.health, request.nickname, request.sex, request.favoriteFood);
            return ResponseEntity.ok("Created! new animal:\n" + createdAnimal.toString());
        } catch (RuntimeException e) {
            System.out.println(e.getCause());
            return ResponseEntity.status(666).body(e.getCause().toString());
        }
    }


    private Animal _createAnimal(String type, int health, String nickname,
                              String sex, String favoriteFood) {
        if (type.equals("monkey")) {
            Animal animal = new Monkey(health, nickname, sex, favoriteFood);
            zoo.addAnimal(animal);
            return animal;

        }
        if (type.equals("rabbit")) {
            Animal animal = new Rabbit(health, nickname, sex, favoriteFood);
            zoo.addAnimal(animal);
            return animal;
        }
        if (type.equals("tiger")) {
            Animal animal = new Tiger(health, nickname, sex, favoriteFood);
            zoo.addAnimal(animal);
            return animal;
        }
        if (type.equals("wolf")) {
            Animal animal = new Wolf(health, nickname, sex, favoriteFood);
            zoo.addAnimal(animal);
            return animal;
        }
        System.out.println("unknown animal");
        return null;
    }

    private Thing _createThing(String type) {
        if (type.equals("table")) {
            Thing thing = new Table();
            zoo.addThing(thing);
            return thing;
        }
        if (type.equals("computer")) {
            Thing thing = new Computer();
            zoo.addThing(thing);
            return thing;
        }
        System.out.println("unknown thing");
        return null;
    }
}

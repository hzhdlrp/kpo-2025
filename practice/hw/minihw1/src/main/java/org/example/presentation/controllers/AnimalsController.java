package org.example.presentation.controllers;

import org.example.application.zoo.Zoo;
import org.example.domain.animals.Animal;
import org.example.domain.animals.herbos.Monkey;
import org.example.domain.animals.herbos.Rabbit;
import org.example.domain.animals.predators.Tiger;
import org.example.domain.animals.predators.Wolf;
import org.example.domain.things.Computer;
import org.example.domain.things.Table;
import org.example.domain.things.Thing;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.infrastructure.services.*;
import org.example.presentation.requests.AnimalRequest;
import org.example.presentation.requests.EnclosureRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@Tag(name = "Animals API", description = "Управление животными в зоопарке")
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
    private final Zoo zoo;

    @Autowired
    public AnimalsController(Zoo zoo) {
        this.zoo = zoo;
    }

    @PostMapping("/animal")
    @Operation(summary = "Создать животное")
    public ResponseEntity<String> createAnimal(@RequestBody @Parameter(description = "Данные животного")
                                               AnimalRequest request) {
        try {
            Animal createdAnimal = _createAnimal(request.type, request.health, request.nickname, request.sex, request.favoriteFood);

            return ResponseEntity.ok("Created! new animal:\n" + createdAnimal.toString());
        } catch (RuntimeException e) {
//            System.out.println(e.getCause());
            return ResponseEntity.status(666).body(e.getMessage());
        }
    }

    @PostMapping("/thing")
    @Operation(summary = "Создать вещь")
    public ResponseEntity<String> createThing(@RequestBody @Parameter(description = "вид вещи") String type) {
        try {
            var createdThing = _createThing(type);
            return ResponseEntity.ok("Created! new thingID:" + String.valueOf(createdThing.getNumber()) + "\n");
        } catch (RuntimeException e) {
//            System.out.println(e.getCause());
            return ResponseEntity.status(666).body(e.getMessage());
        }
    }

    @PostMapping("/feed/{nickname}")
    @Operation(summary = "покормить животное")
    public ResponseEntity<String> feedAnimal(@PathVariable @Parameter(description = "Кличка животного") String nickname) {
        String str = feedingAndHealingOrganizationService.feedByNickname(nickname);
        return ResponseEntity.ok(str);
    }

    @PostMapping("/heal/{nickname}")
    @Operation(summary = "лечить животное")
    public ResponseEntity<String> healAnimal(@PathVariable @Parameter(description = "Кличка животного") String nickname) {
        String str = feedingAndHealingOrganizationService.healByNickname(nickname);
        return ResponseEntity.ok(str);
    }

    @PostMapping("/move/{nickname}")
    @Operation(summary = "переместить в вольер")
    public ResponseEntity<String> moveTo(@PathVariable @Parameter(description = "Кличка животного") String nickname,
                                         @RequestBody @Parameter(description = "id вольера") int enclosureId) {
        try {
            var success = animalTransferService.changeEnclosure(nickname, enclosureId);
            if (success) {
                return ResponseEntity.ok("enclosure successful changed\n");
            }
            else {
                return ResponseEntity.status(666).body("error: no enclosure or no space");
            }
        } catch (RuntimeException e) {
//            System.out.println(e.getCause());
            return ResponseEntity.status(666).body(e.getMessage());
        }
    }

    @PostMapping("create/enclosure")
    @Operation(summary = "создать вольер")
    public ResponseEntity<String> createEnclosure(@RequestBody @Parameter(description = "данные о вольере") EnclosureRequest enclosureRequest) {
        var id = animalTransferService.addEnclosure(enclosureRequest.capacity, enclosureRequest.type);
        if (id.getCode() != -1) {
            return ResponseEntity.ok("created, enclosureID = " + id.getResult() + "\n");
        } else {
            return ResponseEntity.status(666).body(id.getResult());
        }
    }

    @Operation(
            summary = "Получить общее количество животных",
            description = "Возвращает общее число животных в зоопарке",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Успешный запрос",
                            content = @Content(
                                    schema = @Schema(implementation = Integer.class),
                                    examples = @ExampleObject(value = "42")
                            )
                    )
            }
    )
    @GetMapping("/count")
    public ResponseEntity<Integer> getAnimalsCount() {
        return ResponseEntity.ok(zooStatisticsService.getAnimalsNumber());
    }

    @Operation(
            summary = "Получить список травоядных",
            description = "Возвращает список всех травоядных животных с их именами и кличками",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Успешный запрос",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = List.class),
                                    examples = @ExampleObject(value = "[\"Zibra Марти\"]")
                            )
                    )
            }
    )
    @GetMapping("/herbivores")
    public ResponseEntity<List<String>> getAllHerbivores() {
        return ResponseEntity.ok(zooStatisticsService.getHerbosNames());
    }

    @Operation(
            summary = "Получить список хищников",
            description = "Возвращает список всех хищных животных с их именами и кличками",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Успешный запрос",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = List.class),
                                    examples = @ExampleObject(value = "[\"Tiger Шерхан\"]")
                            )
                    )
            }
    )
    @GetMapping("/predators")
    public ResponseEntity<List<String>> getAllPredators() {
        return ResponseEntity.ok(zooStatisticsService.getPredatorsNames());
    }

    @Operation(
            summary = "Получить полную информацию о животных",
            description = "Возвращает детальную информацию обо всех животных в зоопарке",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Успешный запрос",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = List.class),
                                    examples = @ExampleObject(
                                            value = "\"Tiger Matras(Health: 20, Favorite food: meat, Sex: male, Food value: 20, Status: healthy)\"]"
                                    )
                            )
                    )
            }
    )
    @GetMapping("/all")
    public ResponseEntity<List<String>> getAllAnimalsInfo() {
        return ResponseEntity.ok(zooStatisticsService.getAllAnimals());
    }


    private Animal _createAnimal(String type, int health, String nickname,
                              String sex, String favoriteFood) {
        if (type.equals("monkey")) {
            Animal animal = new Monkey(health, nickname, sex, favoriteFood);
            var res = zoo.addAnimal(animal);
            if (res == 0) {
                return animal;
            } else if (res == -2) {
                throw new RuntimeException("too low health");
            } else {
                throw new RuntimeException("animal with this name already exists");
            }

        }
        if (type.equals("rabbit")) {
            Animal animal = new Rabbit(health, nickname, sex, favoriteFood);
            var res = zoo.addAnimal(animal);
            if (res == 0) {
                return animal;
            } else if (res == -2) {
                throw new RuntimeException("too low health");
            } else {
                throw new RuntimeException("animal with this name already exists");
            }
        }
        if (type.equals("tiger")) {
            Animal animal = new Tiger(health, nickname, sex, favoriteFood);
            var res = zoo.addAnimal(animal);
            if (res == 0) {
                return animal;
            } else if (res == -2) {
                throw new RuntimeException("too low health");
            } else {
                throw new RuntimeException("animal with this name already exists");
            }
        }
        if (type.equals("wolf")) {
            Animal animal = new Wolf(health, nickname, sex, favoriteFood);
            var res = zoo.addAnimal(animal);
            if (res == 0) {
                return animal;
            } else if (res == -2) {
                throw new RuntimeException("too low health");
            } else {
                throw new RuntimeException("animal with this name already exists");
            }
        }
        throw new RuntimeException("unknown animal type");
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

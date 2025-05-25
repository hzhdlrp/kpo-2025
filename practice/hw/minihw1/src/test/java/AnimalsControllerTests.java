package org.example.presentation.controllers;

import org.example.Main;
import org.example.application.zoo.Zoo;
import org.example.domain.animals.Animal;
import org.example.domain.things.Table;
import org.example.infrastructure.services.*;
import org.example.presentation.requests.AnimalRequest;
import org.example.presentation.requests.EnclosureRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = Main.class)
@ExtendWith(SpringExtension.class)
class AnimalsControllerTests {

    @Mock
    private Zoo zoo;

    @Mock
    private AliveService aliveService;

    @Mock
    private ZooStatisticsService zooStatisticsService;

    @Mock
    private AnimalTransferService animalTransferService;

    @Mock
    private FeedingAndHealingOrganizationService feedingAndHealingOrganizationService;

    @Mock
    private ThingService thingService;

    @InjectMocks
    private AnimalsController animalsController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createAnimal_Success() {
        AnimalRequest request = new AnimalRequest();
        request.type = "monkey";
        request.health = 100;
        request.nickname = "King Kong";
        request.sex = "male";
        request.favoriteFood = "bananas";

        when(zoo.addAnimal(any(Animal.class))).thenReturn(0);

        ResponseEntity<String> response = animalsController.createAnimal(request);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody().contains("Created! new animal:"));
    }

    @Test
    void createAnimal_InvalidType() {
        AnimalRequest request = new AnimalRequest();
        request.type = "dragon"; // несуществующий тип

        ResponseEntity<String> response = animalsController.createAnimal(request);

        assertEquals(666, response.getStatusCodeValue());
        assertTrue(response.getBody().contains("unknown animal type"));
    }

    @Test
    void feedAnimal_Success() {
        when(feedingAndHealingOrganizationService.feedByNickname(anyString())).thenReturn("Fed successfully");

        ResponseEntity<String> response = animalsController.feedAnimal("Simba");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Fed successfully", response.getBody());
    }

    @Test
    void healAnimal_Success() {
        when(feedingAndHealingOrganizationService.healByNickname(anyString())).thenReturn("Healed successfully");

        ResponseEntity<String> response = animalsController.healAnimal("Simba");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Healed successfully", response.getBody());
    }

    @Test
    void getAnimalsCount_Success() {
        when(zooStatisticsService.getAnimalsNumber()).thenReturn(5);

        ResponseEntity<Integer> response = animalsController.getAnimalsCount();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(5, response.getBody());
    }

    @Test
    void getAllHerbivores_Success() {
        when(zooStatisticsService.getHerbosNames()).thenReturn(List.of("Monkey King Kong", "Rabbit Bugs"));

        ResponseEntity<List<String>> response = animalsController.getAllHerbivores();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().size());
        assertTrue(response.getBody().contains("Monkey King Kong"));
    }

    @Test
    void getAllPredators_Success() {
        when(zooStatisticsService.getPredatorsNames()).thenReturn(List.of("Tiger Sherkhan", "Wolf Grey"));

        ResponseEntity<List<String>> response = animalsController.getAllPredators();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().size());
        assertTrue(response.getBody().contains("Tiger Sherkhan"));
    }

    @Test
    void createEnclosure_Success() {
        EnclosureRequest request = new EnclosureRequest();
        request.capacity = 10;
        request.type = "predator";

        when(animalTransferService.addEnclosure(anyInt(), anyString()))
                .thenReturn(new ResultType<>(0, "1"));

        ResponseEntity<String> response = animalsController.createEnclosure(request);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody().contains("created, enclosureID = 1"));
    }

    @Test
    void moveToEnclosure_Success() {
        when(animalTransferService.changeEnclosure(anyString(), anyInt())).thenReturn(true);

        ResponseEntity<String> response = animalsController.moveTo("Simba", 1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody().contains("enclosure successful changed"));
    }

    @Test
    void moveToEnclosure_Failure() {
        when(animalTransferService.changeEnclosure(anyString(), anyInt())).thenReturn(false);

        ResponseEntity<String> response = animalsController.moveTo("Simba", 1);

        assertEquals(666, response.getStatusCodeValue());
        assertTrue(response.getBody().contains("error: no enclosure or no space"));
    }

}
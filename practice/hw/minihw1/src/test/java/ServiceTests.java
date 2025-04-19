import org.example.Main;
import org.example.domain.animals.herbos.Monkey;
import org.example.domain.animals.herbos.Rabbit;
import org.example.domain.animals.predators.Tiger;
import org.example.domain.animals.predators.Wolf;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.example.infrastructure.services.AliveService;
import org.example.infrastructure.services.ThingService;
import org.example.domain.things.Computer;
import org.example.domain.things.Table;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest(classes = Main.class)
@ExtendWith(SpringExtension.class)
public class ServiceTests {
    @Autowired
    AliveService aliveService;

    @Autowired
    ThingService thingService;

    @Test
    @DisplayName("Setting AliveService test")
    public void setAliveService() {
        aliveService.addAnimal(new Monkey(1, "mimi", "female", "banana"));
        aliveService.addAnimal(new Rabbit(1, "krosh", "male", "grass"));
        aliveService.addAnimal(new Tiger(1, "tig", "male", "meet"));
        aliveService.addAnimal(new Wolf(1, "wolf", "male", "meet"));

        aliveService.printAnimalsNumber();
        aliveService.printAllFoodCount();
        aliveService.printContactList();
        aliveService.printAnimalsInfo();
    }

    @Test
    @DisplayName("Setting ThingService test")
    public void setThingService() {
        thingService.addThing(new Computer());
        thingService.addThing(new Table());
        thingService.addThing(new Computer());
        thingService.addThing(new Table());

        thingService.printThingsInfo();
    }
}

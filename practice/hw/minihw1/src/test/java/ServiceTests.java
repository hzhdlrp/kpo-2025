import domain.animals.herbos.Monkey;
import domain.animals.herbos.Rabbit;
import domain.animals.predators.Tiger;
import domain.animals.predators.Wolf;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import infrastructure.storage.AliveService;
import infrastructure.storage.ThingService;
import domain.things.Computer;
import domain.things.Table;

public class ServiceTests {
    @Autowired
    AliveService aliveService;

    @Autowired
    ThingService thingService;

    @Test
    @DisplayName("Setting AliveService test")
    public void setAliveService() {
        aliveService.addAnimal(new Monkey(13));
        aliveService.addAnimal(new Rabbit(12));
        aliveService.addAnimal(new Tiger(7));
        aliveService.addAnimal(new Wolf(9));

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

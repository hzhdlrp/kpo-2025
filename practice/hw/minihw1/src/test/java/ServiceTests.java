import animals.herbos.Monkey;
import animals.herbos.Rabbit;
import animals.predators.Tiger;
import animals.predators.Wolf;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import services.AliveService;
import services.ThingService;
import things.Computer;
import things.Table;

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

import org.example.Main;
import org.example.domain.animals.herbos.Monkey;
import org.example.domain.animals.herbos.Rabbit;
import org.example.domain.animals.predators.Tiger;
import org.example.domain.animals.predators.Wolf;
import org.example.infrastructure.services.AliveService;
import org.example.infrastructure.services.ThingService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.example.application.zoo.Zoo;
import org.example.domain.things.Computer;
import org.example.domain.things.Table;
import org.example.application.veterinary.VetClinic;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.junit.jupiter.api.extension.ExtendWith;

@SpringBootTest(classes = Main.class)
@ExtendWith(SpringExtension.class)
public class ZooTests {
    @Autowired
    private VetClinic vetClinic;
    @Autowired
    private AliveService aliveService;
    @Autowired
    private ThingService thingService;
    @Autowired
    private Zoo zoo;

    @Test
    @DisplayName("Zoo Animals test")
    public void zooAnimalsTest() {

        zoo.addAnimal(new Monkey(13, "bebebe", "female", "banana"));
        zoo.addAnimal(new Rabbit(12, "hihi", "female", "grass"));
        zoo.addAnimal(new Tiger(7, "bigboss", "male", "baranina"));
        zoo.addAnimal(new Wolf(9, "zuzu", "female", "chicken"));

        zoo.printAllFoodCount();
        zoo.printAnimalsInfo();
        zoo.printContactList();
        zoo.printAnimalsNumber();
    }

    @Test
    @DisplayName("Zoo Things test")
    public void zooThingsTest() {
        zoo.addThing(new Computer());
        zoo.addThing(new Table());
        zoo.addThing(new Computer());
        zoo.addThing(new Table());

        zoo.printThingsInfo();
    }
}

import domain.animals.herbos.Monkey;
import domain.animals.herbos.Rabbit;
import domain.animals.predators.Tiger;
import domain.animals.predators.Wolf;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import application.app.Zoo;
import domain.things.Computer;
import domain.things.Table;
import application.veterinary.VetClinic;

public class ZooTests {
    @Autowired
    Zoo zoo;

    @Test
    @DisplayName("Zoo Animals test")
    public void zooAnimalsTest() {
        zoo.setClinic(new VetClinic(10));

        zoo.addAnimal(new Monkey(13));
        zoo.addAnimal(new Rabbit(12));
        zoo.addAnimal(new Tiger(7));
        zoo.addAnimal(new Wolf(9));

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

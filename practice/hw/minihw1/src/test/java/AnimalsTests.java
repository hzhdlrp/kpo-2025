import animals.Animal;
import animals.herbos.Monkey;
import animals.herbos.Rabbit;
import animals.predators.Tiger;
import animals.predators.Wolf;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AnimalsTests {
    @Test
    @DisplayName("creating animals test")
    public void test() {
        Animal animal = new Wolf(1);
        Assertions.assertNotNull(animal);

        animal = new Tiger(1);
        Assertions.assertNotNull(animal);

        animal = new Rabbit(1);
        Assertions.assertNotNull(animal);

        animal = new Monkey(1);
        Assertions.assertNotNull(animal);
    }
}

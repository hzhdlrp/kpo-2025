import domain.animals.Animal;
import domain.animals.herbos.Monkey;
import domain.animals.herbos.Rabbit;
import domain.animals.predators.Tiger;
import domain.animals.predators.Wolf;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AnimalsTests {
    @Test
    @DisplayName("creating animals test")
    public void test() {
        Animal animal = new Wolf(1, "wolf", "male", "meet");
        Assertions.assertNotNull(animal);

        animal = new Tiger(1, "tig", "male", "meet");
        Assertions.assertNotNull(animal);

        animal = new Rabbit(1, "krosh", "male", "grass");
        Assertions.assertNotNull(animal);

        animal = new Monkey(1, "mimi", "female", "banana");
        Assertions.assertNotNull(animal);
    }
}

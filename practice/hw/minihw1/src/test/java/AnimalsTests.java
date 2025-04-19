import org.example.Main;
import org.example.domain.animals.Animal;
import org.example.domain.animals.herbos.Monkey;
import org.example.domain.animals.herbos.Rabbit;
import org.example.domain.animals.predators.Tiger;
import org.example.domain.animals.predators.Wolf;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest(classes = Main.class)
@ExtendWith(SpringExtension.class)
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

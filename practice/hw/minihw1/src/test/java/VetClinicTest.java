import org.example.Main;
import org.example.domain.animals.Animal;
import org.example.domain.animals.herbos.Monkey;
import org.example.domain.animals.predators.Tiger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.example.application.veterinary.VetClinic;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest(classes = Main.class)
@ExtendWith(SpringExtension.class)
public class VetClinicTest {
    @Autowired
    VetClinic vetClinic;

    @Test
    @DisplayName("VetClinic checking health test")
    public void vetClinicTest() {
        vetClinic.setCriteria(5);
        Animal animal = new Monkey(15, "mimi", "female", "banana");
        Assertions.assertEquals(true, vetClinic.check(animal));
        animal = new  Tiger(3, "tig", "male", "meet");
        Assertions.assertEquals(false, vetClinic.check(animal));
    }
}

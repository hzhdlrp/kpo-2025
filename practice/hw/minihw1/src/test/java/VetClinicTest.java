import animals.Animal;
import animals.herbos.Monkey;
import animals.predators.Tiger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import veterinary.VetClinic;

public class VetClinicTest {
    @Autowired
    VetClinic vetClinic;

    @Test
    @DisplayName("VetClinic checking health test")
    public void vetClinicTest() {
        vetClinic.setCriteria(5);
        Animal animal = new Monkey(15);
        Assertions.assertEquals(true, vetClinic.check(animal));
        animal = new Tiger(3);
        Assertions.assertEquals(false, vetClinic.check(animal));
    }
}

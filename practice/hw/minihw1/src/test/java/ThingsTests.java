import domain.animals.Animal;
import domain.animals.predators.Tiger;
import domain.animals.predators.Wolf;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import domain.things.Computer;
import domain.things.Table;
import domain.things.Thing;

public class ThingsTests {
    @Test
    @DisplayName("creating things test")
    public void test() {
        Thing thing = new Computer();
        Assertions.assertNotNull(thing);

        thing = new Table();
        Assertions.assertNotNull(thing);
    }
}

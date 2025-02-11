import animals.Animal;
import animals.predators.Tiger;
import animals.predators.Wolf;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import things.Computer;
import things.Table;
import things.Thing;

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

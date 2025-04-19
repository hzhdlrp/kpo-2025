import org.example.Main;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.example.domain.things.Computer;
import org.example.domain.things.Table;
import org.example.domain.things.Thing;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest(classes = Main.class)
@ExtendWith(SpringExtension.class)
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

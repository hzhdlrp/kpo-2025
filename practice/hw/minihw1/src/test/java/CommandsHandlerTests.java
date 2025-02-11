import app.CommandsHandler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import services.Zoo;

import java.io.ByteArrayInputStream;

public class CommandsHandlerTests {
    @Autowired
    CommandsHandler handler;

    @Test
    @DirtiesContext
    @DisplayName("Reading criteria test")
    public void readingCritTest() {
        String input = "42";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        Assertions.assertEquals(42, handler.readCriteria());
    }

    @Test
    @DirtiesContext
    @DisplayName("Handelling command add test")
    public void addTest() {
        String input = "add animal monkey 42";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        Assertions.assertEquals(true, handler.handleOneCommand(new Zoo(10)));

        input = "add animal tiger 42";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        Assertions.assertEquals(true, handler.handleOneCommand(new Zoo(10)));
    }

    @Test
    @DirtiesContext
    @DisplayName("Handelling command print test")
    public void printTest() {
        String input = "add animal rabbit 42";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        Assertions.assertEquals(true, handler.handleOneCommand(new Zoo(10)));

        input = "print contact";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        Assertions.assertEquals(true, handler.handleOneCommand(new Zoo(10)));

        input = "print animals";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        Assertions.assertEquals(true, handler.handleOneCommand(new Zoo(10)));
    }

    @Test
    @DirtiesContext
    @DisplayName("Handelling command end test")
    public void endTest() {
        String input = "end";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        Assertions.assertEquals(false, handler.handleOneCommand(new Zoo(10)));
    }
}

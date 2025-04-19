import org.example.domain.animals.Animal;
import org.example.domain.animals.herbos.Herbo;
import org.example.domain.animals.predators.Predator;
import org.example.domain.cages.Enclosure;
import org.example.domain.cages.EnclosureTypes;
import org.example.infrastructure.events.AnimalMovedEvent;
import org.example.infrastructure.services.AliveService;
import org.example.infrastructure.services.AnimalTransferService;
import org.example.infrastructure.services.ResultType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AnimalTransferServiceTest {

    @Mock
    private AliveService aliveService;

    @Mock
    private AnimalMovedEvent animalMovedEvent;

    @InjectMocks
    private AnimalTransferService animalTransferService;

    @Test
    void addEnclosure_SuccessForHerbo() {
        ResultType<String> result = animalTransferService.addEnclosure(10, "herbo");

        assertEquals(0, result.getCode());
        assertNotNull(result.getResult());
    }

    @Test
    void addEnclosure_SuccessForPredator() {
        ResultType<String> result = animalTransferService.addEnclosure(10, "predator");

        assertEquals(0, result.getCode());
        assertNotNull(result.getResult());
    }

    @Test
    void addEnclosure_InvalidType() {
        ResultType<String> result = animalTransferService.addEnclosure(10, "invalid_type");

        assertEquals(-1, result.getCode());
        assertEquals("unknown enclosure type", result.getResult());
    }

    @Test
    void putAnimalByNicknameToEnclosureById_SuccessForHerbo() {
        Herbo mockHerbo = mock(Herbo.class);
        when(aliveService.getAnimalByNickname("Bambi")).thenReturn(mockHerbo);

        ResultType<String> enclosureResult = animalTransferService.addEnclosure(10, "herbo");
        assertTrue(enclosureResult.getCode() == 0);

        boolean result = animalTransferService.putAnimalByNicknameToEnclosureById(
                "Bambi",
                Integer.parseInt(enclosureResult.getResult())
        );

        assertTrue(result);
        verify(animalMovedEvent).moved("Bambi", Integer.parseInt(enclosureResult.getResult()));
    }

    @Test
    void putAnimalByNicknameToEnclosureById_WrongType() {
        Herbo mockHerbo = mock(Herbo.class);
        when(aliveService.getAnimalByNickname("Bambi")).thenReturn(mockHerbo);

        ResultType<String> enclosureResult = animalTransferService.addEnclosure(10, "predator");
        assertTrue(enclosureResult.getCode() == 0);

        boolean result = animalTransferService.putAnimalByNicknameToEnclosureById(
                "Bambi",
                Integer.parseInt(enclosureResult.getResult())
        );

        assertFalse(result);
        verify(animalMovedEvent, never()).moved(anyString(), anyInt());
    }

    @Test
    void changeEnclosure_Success() {
        Herbo mockHerbo = mock(Herbo.class);
        when(aliveService.getAnimalByNickname("Bambi")).thenReturn(mockHerbo);

        ResultType<String> oldEnclosureResult = animalTransferService.addEnclosure(10, "herbo");
        ResultType<String> newEnclosureResult = animalTransferService.addEnclosure(10, "herbo");

        assertAll(
                () -> assertEquals(0, oldEnclosureResult.getCode()),
                () -> assertEquals(0, newEnclosureResult.getCode())
        );

        assertTrue(animalTransferService.putAnimalByNicknameToEnclosureById(
                "Bambi",
                Integer.parseInt(oldEnclosureResult.getResult())
        ));

        boolean result = animalTransferService.changeEnclosure(
                "Bambi",
                Integer.parseInt(newEnclosureResult.getResult())
        );

        assertTrue(result);
        verify(animalMovedEvent).moved("Bambi", Integer.parseInt(newEnclosureResult.getResult()));
    }
}
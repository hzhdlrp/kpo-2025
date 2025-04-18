package infrastructure.services;

import domain.animals.Animal;
import domain.animals.herbos.Herbo;
import domain.animals.predators.Predator;
import domain.cages.Enclosure;
import domain.cages.EnclosureTypes;
import infrastructure.events.AnimalMovedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class AnimalTransferService {
    private Map<Integer, Enclosure> enclosures = new HashMap<>();
    @Autowired
    private AliveService aliveService;

    private AnimalMovedEvent animalMovedEvent = new AnimalMovedEvent();

    public ResultType<String> addEnclosure(int capacity, String type) {
        EnclosureTypes enclosureType;
        if (type.equals("herbo")) enclosureType = EnclosureTypes.HERBO;
        else if (type.equals("predator")) enclosureType = EnclosureTypes.PREDATOR;
        else {
            System.out.println("unknown enclosure type");
            return new ResultType<>(-1, "unknown enclosure type");
        }

        int id = 0;
        while (enclosures.containsKey(id)) id++;
        enclosures.put(id, Enclosure.builder()
                        .capacity(capacity)
                        .type(enclosureType)
                        .id(id)
                .build());
        return new ResultType<>(0,String.valueOf(id));
    }

    public boolean putAnimalByNicknameToEnclosureById(String nickname, int enclosureId) {
        if (aliveService.getAnimalByNickname(nickname) instanceof Herbo &&
                !enclosures.get(enclosureId).getType().equals(EnclosureTypes.HERBO)) {
            System.out.println("animal " + nickname + " has not type herbo for that enclosure");
            return false;
        }
        if (aliveService.getAnimalByNickname(nickname) instanceof Predator &&
                !enclosures.get(enclosureId).getType().equals(EnclosureTypes.PREDATOR)) {
            System.out.println("animal " + nickname + " has not type predator for that enclosure");
            return false;
        }
        enclosures.get(enclosureId).addAnimal(nickname);
        animalMovedEvent.moved(nickname, enclosureId);
        return true;
    }

    private void removeAnimalByNicknameFromEnclosure(String nickname) {
        enclosures.entrySet().forEach(pair -> {
            if (pair.getValue().containsAnimal(nickname)) {
                pair.getValue().deleteAnimal(nickname);
                return;
            }
        });
    }

    public boolean changeEnclosure(String nickname, int newEnclosureId) {
        if (putAnimalByNicknameToEnclosureById(nickname, newEnclosureId)) {
            removeAnimalByNicknameFromEnclosure(nickname);
            return true;
        }
        return false;
    }

}

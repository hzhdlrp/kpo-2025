package org.example.domain.cages;

import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Builder
public class Enclosure {
    private int id;
    private List<String> nicknames = new ArrayList<>();
    @Getter
    private int capacity;
    @Getter
    private EnclosureTypes type;

    public void addAnimal(String nickname) {
        if (nicknames.size() < capacity) {
            nicknames.add(nickname);
        }  else {
            System.out.println("error, enclosure has no empty space");
        }
    }

    public void deleteAnimal(String nickname) {
        nicknames.remove(nickname);
    }

    public boolean containsAnimal(String nickname) {
        return nicknames.contains(nickname);
    }
}

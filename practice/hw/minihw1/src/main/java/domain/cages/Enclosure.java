package domain.cages;

import domain.interfaces.IAlive;
import lombok.Builder;

import java.util.ArrayList;
import java.util.List;

@Builder
public class Enclosure {
    private int id;
    private List<IAlive> animals = new ArrayList<>();
    private int capacity;
    private String type;
}

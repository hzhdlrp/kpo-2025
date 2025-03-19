package hse.kpo.domains;

import hse.kpo.interfaces.IEngine;
import lombok.Getter;
import lombok.ToString;

@ToString
public class Catamaran {

    @Getter
    private int speed;

    private IEngine engine;

    public Catamaran(int speed, IEngine engine) {
        this.speed = speed;
        this.engine = engine;
    }
}

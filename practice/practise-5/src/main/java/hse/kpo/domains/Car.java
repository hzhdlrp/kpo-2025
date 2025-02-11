package hse.kpo.domains;

import hse.kpo.interfaces.IEngine;
import lombok.Getter;
import lombok.ToString;
import org.springframework.stereotype.Service;

/**
 *
 */
@ToString

public class Car {

    private IEngine engine;

    @Getter
    private int VIN;

    /**
     *
     * @param VIN
     * @param engine
     */
    public Car(int VIN, IEngine engine) {
        this.VIN = VIN;
        this.engine = engine;
    }

    /**
     *
     * @param customer
     * @return
     */
    public boolean isCompatible(Customer customer) {
        return this.engine.isCompatible(customer); // внутри метода просто вызываем соответствующий метод двигателя
    }
}

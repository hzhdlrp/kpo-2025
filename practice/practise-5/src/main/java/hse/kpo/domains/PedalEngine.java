package hse.kpo.domains;

import hse.kpo.interfaces.IEngine;
import lombok.Getter;
import lombok.ToString;
import org.springframework.stereotype.Service;

/**
 *
 */
@ToString
@Getter

public class PedalEngine implements IEngine {
    private final int size;

    /**
     *
     * @param customer - покупатель, с которым мы сравниваем двигатель
     * @return
     */
    @Override
    public boolean isCompatible(Customer customer) {
        return customer.getLegPower() > 5;
    }

    /**
     *
     * @param size
     */
    public PedalEngine(int size) {
        this.size = size;
    }
}

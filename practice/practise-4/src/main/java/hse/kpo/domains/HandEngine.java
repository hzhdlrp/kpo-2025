package hse.kpo.domains;

import hse.kpo.interfaces.IEngine;
import lombok.ToString;
import org.springframework.stereotype.Service;

/**
 *
 */
@ToString

public class HandEngine implements IEngine {
    /**
     *
     * @param customer - покупатель, с которым мы сравниваем двигатель
     * @return
     */
    @Override
    public boolean isCompatible(Customer customer) {
        return customer.getHandPower() > 5;
    }
}

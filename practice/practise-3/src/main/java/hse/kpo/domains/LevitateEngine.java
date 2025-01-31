package hse.kpo.domains;

import lombok.ToString;
import hse.kpo.interfaces.IEngine;
import hse.kpo.domains.Customer;
import org.springframework.stereotype.Service;

/**
 *
 */
@ToString

public class LevitateEngine implements IEngine {
    /**
     *
     * @param customer - покупатель, с которым мы сравниваем двигатель
     * @return
     */
    @Override
    public boolean isCompatible(Customer customer) {
        return customer.getIQ() > 300;
    }
}

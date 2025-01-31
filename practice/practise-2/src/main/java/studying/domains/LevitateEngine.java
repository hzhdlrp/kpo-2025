package studying.domains;

import lombok.Getter;
import lombok.ToString;
import studying.interfaces.IEngine;

@ToString
public class LevitateEngine implements IEngine {
    @Override
    public boolean isCompatible(Customer customer) {
        return customer.getIQ() > 300;
    }
}
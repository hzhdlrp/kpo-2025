package hse.kpo.domains.catamarans;

import hse.kpo.domains.customers.Customer;
import hse.kpo.enums.ProductionTypes;
import hse.kpo.interfaces.Engine;
import hse.kpo.interfaces.Transport;
import lombok.Getter;
import lombok.ToString;

/**
 * Класс хранящий информацию о катамаране.
 */
@ToString
public class Catamaran implements Transport {

    @Getter
    private Engine engine;

    private int vin;

    @Override
    public int getVin() {
        return this.vin;
    }

    @Override
    public String getEngineType() {
        return engine.toString();
    }

    @Override
    public String getTransportType() {
        return "Catamaran";
    }

    public Catamaran(int vin, Engine engine) {
        this.vin = vin;
        this.engine = engine;
    }

    @Override
    public boolean isCompatible(Customer customer) {
        return this.engine.isCompatible(customer, ProductionTypes.CATAMARAN);
    }
}

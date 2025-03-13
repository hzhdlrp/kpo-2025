package hse.kpo.domains.cars;

import hse.kpo.domains.customers.Customer;
import hse.kpo.enums.ProductionTypes;
import hse.kpo.interfaces.Engine;
import hse.kpo.interfaces.Transport;
import lombok.Getter;
import lombok.ToString;

/**
 * Класс хранящий информацию о машине.
 */
@ToString
public class Car implements Transport {

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
        return "Car";
    }

    public Car(int vin, Engine engine) {
        this.vin = vin;
        this.engine = engine;
    }

    @Override
    public boolean isCompatible(Customer customer) {
        return this.engine.isCompatible(customer, ProductionTypes.CAR);
    }
}

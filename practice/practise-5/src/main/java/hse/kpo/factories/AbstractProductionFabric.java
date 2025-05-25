package hse.kpo.factories;

import hse.kpo.domains.Car;
import hse.kpo.domains.Catamaran;
import hse.kpo.interfaces.ICarFactory;
import hse.kpo.interfaces.ICatamaranFactory;
import hse.kpo.interfaces.ICustomerProvider;
import hse.kpo.params.EmptyEngineParams;
import hse.kpo.params.PedalEngineParams;
import org.springframework.stereotype.Component;

@Component
public class AbstractProductionFabric {

    ICarFactory handCarFactory;
    ICarFactory pedalCarFactory;

    public AbstractProductionFabric() {
        this.handCarFactory = new HandCarFactory();
        this.pedalCarFactory = new PedalCarFactory();
    }

    public void createCarForCustomers(ICustomerProvider customerProvider) {

        customerProvider.getCustomers().forEach(customer -> {
            if (customer.getLegPower() > customer.getHandPower()) {
                PedalEngineParams params = new PedalEngineParams(1);
                Car car = pedalCarFactory.createCar(params, 0);
                if (car.isCompatible(customer)) {
                    customer.setCar(car);
                } else {
                    car = handCarFactory.createCar(EmptyEngineParams.DEFAULT, 0);
                    if (car.isCompatible(customer)) {
                        customer.setCar(car);
                    }
                }
            } else {
                PedalEngineParams params = new PedalEngineParams(1);
                Car car = handCarFactory.createCar(EmptyEngineParams.DEFAULT, 0);
                car = pedalCarFactory.createCar(params, 0);
                if (car.isCompatible(customer)) {
                    customer.setCar(car);
                } else {
                    car = pedalCarFactory.createCar(params, 0);
                    if (car.isCompatible(customer)) {
                        customer.setCar(car);
                    }
                }
            }
        });
    }
}

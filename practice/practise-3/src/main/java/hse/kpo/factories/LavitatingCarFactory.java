package hse.kpo.factories;

import hse.kpo.domains.Car;
import hse.kpo.domains.LevitateEngine;
import hse.kpo.interfaces.ICarFactory;
import hse.kpo.params.EmptyEngineParams;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class LavitatingCarFactory implements ICarFactory<EmptyEngineParams> {
    @Override
    public Car createCar(EmptyEngineParams carParams, int carNumber) {
        var engine = new LevitateEngine();

        return new Car(carNumber, engine);
    }
}
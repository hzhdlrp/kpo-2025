package studying.factories;

import studying.domains.Car;
import studying.domains.HandEngine;
import studying.domains.LevitateEngine;
import studying.interfaces.ICarFactory;
import studying.params.EmptyEngineParams;

public class LavitatingCarFactory implements ICarFactory<EmptyEngineParams> {
    @Override
    public Car createCar(EmptyEngineParams carParams, int carNumber) {
        var engine = new LevitateEngine();

        return new Car(carNumber, engine);
    }
}
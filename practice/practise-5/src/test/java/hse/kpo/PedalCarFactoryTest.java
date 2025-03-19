package hse.kpo;

import hse.kpo.domains.Customer;
import hse.kpo.factories.HandCarFactory;
import hse.kpo.factories.LavitatingCarFactory;
import hse.kpo.factories.PedalCarFactory;
import hse.kpo.params.EmptyEngineParams;
import hse.kpo.params.PedalEngineParams;
import hse.kpo.services.CarService;
import hse.kpo.services.CustomerStorage;
import hse.kpo.services.HseCarService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

@SpringBootTest
class PedalCarFactoryTest {

    @Autowired
    PedalCarFactory pedalCarFactory;

    @Test
    @DisplayName("тест создания педального автомобиля")
    @DirtiesContext
    void pedalCarFactoryTest() {
        var carParams = new PedalEngineParams(12);
        var car = pedalCarFactory.createCar(carParams, 15);
        Assertions.assertNotNull(car);
        Assertions.assertEquals(car.getVIN(), 15);
    }

    @Test
    @DisplayName("тест пододимости покупателя двигателю, тест на ошибку")
    @DirtiesContext
    void pedalCarFactoryCompatibleTest() {
        var carParams = new PedalEngineParams(12);
        var car = pedalCarFactory.createCar(carParams, 15);
        var customer = new Customer("Buba", 1, 4, 108);

        Assertions.assertEquals(false, car.isCompatible(customer));
    }
}
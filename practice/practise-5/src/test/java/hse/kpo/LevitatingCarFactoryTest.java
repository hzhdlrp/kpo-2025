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
class LevitatingCarFactoryTest {

    @Autowired
    LavitatingCarFactory levitatingCarFactory;


    @Test
    @DisplayName("тест создания левитирующего автомобиля")
    @DirtiesContext
    void levitatingCarFactoryTest() {
        var car = levitatingCarFactory.createCar(EmptyEngineParams.DEFAULT, 15);
        Assertions.assertNotNull(car);
        Assertions.assertEquals(car.getVIN(), 15);
    }

    @Test
    @DisplayName("тест подохимости покупателя двигателю")
    @DirtiesContext
    void levitatingCarFactoryCompatibleTest() {
        var car = levitatingCarFactory.createCar(EmptyEngineParams.DEFAULT, 15);
        var customer = new Customer("Biba", 1, 4, 999);

        Assertions.assertEquals(true, car.isCompatible(customer));
    }
}
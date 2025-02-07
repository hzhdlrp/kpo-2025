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

@SpringBootTest
class LevitatingCarFactoryTest {

    @Autowired
    LavitatingCarFactory levitatingCarFactory;

    @Autowired
    Customer customer;

    @Test
    @DisplayName("тест создания левитирующего автомобиля")
    void levitatingCarFactoryTest() {
        var car = levitatingCarFactory.createCar(EmptyEngineParams.DEFAULT, 15);
        Assertions.assertNotNull(car);
        Assertions.assertEquals(car.getVin(), 15);
    }

    @Test
    @DisplayName("тест подохимости покупателя двигателю")
    void levitatingCarFactoryCompatibleTest() {
        var car = levitatingCarFactory.createCar(EmptyEngineParams.DEFAULT, 15);
        customer = new Customer("Biba", 1, 4, 999);

        Assertions.assertEquals(true, car.isCompatible(customer));
    }
}
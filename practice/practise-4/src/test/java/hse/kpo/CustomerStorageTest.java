package hse.kpo;

import hse.kpo.domains.Customer;
import hse.kpo.factories.HandCarFactory;
import hse.kpo.factories.LavitatingCarFactory;
import hse.kpo.factories.PedalCarFactory;
import hse.kpo.params.EmptyEngineParams;
import hse.kpo.params.PedalEngineParams;
import hse.kpo.services.CarService;
import hse.kpo.services.CustomerStorage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static java.util.stream.IntStream.range;

@SpringBootTest
class CustomerStorageTest {

    @Autowired
    CustomerStorage customerStorage;

    @Autowired
    PedalCarFactory pedalCarFactory;

    @Autowired
    HandCarFactory handCarFactory;

    @Autowired
    LavitatingCarFactory levitateCarFactory;

    @Test
    @DisplayName("тест добавления одного клиента")
    void customerStorageAddOneCustomerTest() {
        customerStorage.addCustomer(new Customer("Pipa", 11, 12, 13));
        var storage = customerStorage.getCustomers();
        Assertions.assertNotNull(storage);
        Assertions.assertEquals(storage.size(), 1);
    }

    @Test
    @DisplayName("тест добавления 100 клиентов")
    void customerStorageAddHundredCustomersTest() {
        range(0, 100).forEach(i -> customerStorage.addCustomer(new Customer("Pupa", 11, 12, i)));
        var storage = customerStorage.getCustomers();
        Assertions.assertNotNull(storage);
        Assertions.assertEquals(storage.size(), 100);
    }
}
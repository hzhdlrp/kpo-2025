package hse.kpo;

import hse.kpo.domains.Customer;
import hse.kpo.factories.AbstractProductionFabric;
import hse.kpo.services.CustomerStorage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class FabricTEst {
    @Autowired
    AbstractProductionFabric fabric;

    @Autowired
    CustomerStorage storage;

    @Test
    @DisplayName("тест абстрактной фабрики")
    public void testFabric() {
        storage.addCustomer(new Customer("vivo", 14, 15, 16));

        fabric.createCarForCustomers(storage);

        storage.getCustomers().forEach(customer -> {
                    Assertions.assertNotNull(customer.getCar());
                }
                );
    }
}

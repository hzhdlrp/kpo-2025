package hse.kpo.interfaces;

import hse.kpo.domains.Customer;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 */

public interface ICustomerProvider {
    /**
     *
     * @return
     */
    List<Customer> getCustomers(); // метод возвращает коллекцию только для чтения, так как мы не хотим давать вызывающему коду возможность изменять список
}

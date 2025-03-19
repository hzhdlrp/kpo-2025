package hse.kpo.interfaces;

import hse.kpo.domains.Car;
import hse.kpo.domains.Customer;
import org.springframework.stereotype.Service;

/**
 *
 */

public interface ICarProvider {
    /**
     *
     * @param customer
     * @return
     */
    Car takeCar(Customer customer); // Метод возвращает optional на Car, что означает, что метод может ничего не вернуть
}

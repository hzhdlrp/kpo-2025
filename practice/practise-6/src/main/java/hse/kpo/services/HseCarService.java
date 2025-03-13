package hse.kpo.services;

import hse.kpo.domains.Customer;
import hse.kpo.enums.ProductionTypes;
import hse.kpo.interfaces.cars.CarProvider;
import hse.kpo.interfaces.CustomerProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import hse.kpo.observers.SalesObserver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Сервис продажи машин.
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class HseCarService {

    @Autowired
    private final CarProvider carProvider;

    @Autowired
    private final CustomerProvider customerProvider;

    /**
     * Метод продажи машин
     */
    @Sales()
    public void sellCars() {
        // получаем список покупателей
        var customers = customerProvider.getCustomers();
        // пробегаемся по полученному списку
        customers.stream().filter(customer -> Objects.isNull(customer.getCar()))
                .forEach(customer -> {
                    var car = carProvider.takeCar(customer);
                    if (Objects.nonNull(car)) {
                        customer.setCar(car);
                    } else {
                        System.out.println("No car in CarService");
                    }

                    notifyObserversForSale(customer, ProductionTypes.CAR, car.getVin());
                });

    }

    private final List<SalesObserver> observers = new ArrayList<>();

    public void addObserver(SalesObserver observer) {
        observers.add(observer);
    }

    private void notifyObserversForSale(Customer customer, ProductionTypes productType, int vin) {
        observers.forEach(obs -> obs.onSale(customer, productType, vin));
    }
}
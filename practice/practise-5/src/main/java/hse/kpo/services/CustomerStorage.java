package hse.kpo.services;

import hse.kpo.domains.Customer;
import hse.kpo.interfaces.ICustomerProvider;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
@Component
public class CustomerStorage implements ICustomerProvider {
    private List<Customer> customers = new ArrayList<>();

    /**
     *
     * @return
     */
    @Override
    public List<Customer> getCustomers() {
        return customers;
    }

    /**
     *
     * @param customer
     */
    public void addCustomer(Customer customer)
    {
        customers.add(customer); // просто добавляем покупателя в список
    }
}

package hse.kpo.repositories;

import hse.kpo.domains.Customer;
import hse.kpo.domains.cars.Car;
import hse.kpo.domains.catamarans.Catamaran;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    @Query("""
        DELETE FROM customers c
         WHERE c.name = :name
    """)
    void deleteByName(@Param("name") String name);
}

package br.com.audsat.vehicleinsurancews.repository;

import br.com.audsat.vehicleinsurancews.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    @Query("select c from Customer c " +
            "inner join Driver d on c.driver = d " +
            "where d.document = :driverDocument ")
    Optional<Customer> findByDriverDocument(String driverDocument);
}

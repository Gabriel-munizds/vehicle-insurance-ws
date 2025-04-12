package br.com.audsat.vehicleinsurancews.repository;

import br.com.audsat.vehicleinsurancews.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CarRepository extends JpaRepository<Car, Long> {
    @Query("select c from Car c " +
            "where c.licensePlate = :licensePlate ")
    Optional<Car> findByLicensePlate(String licensePlate);
}

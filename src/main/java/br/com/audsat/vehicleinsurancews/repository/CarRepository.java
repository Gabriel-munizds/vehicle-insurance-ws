package br.com.audsat.vehicleinsurancews.repository;

import br.com.audsat.vehicleinsurancews.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Long> {
}

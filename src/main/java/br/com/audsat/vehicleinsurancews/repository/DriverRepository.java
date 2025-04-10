package br.com.audsat.vehicleinsurancews.repository;

import br.com.audsat.vehicleinsurancews.model.Driver;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DriverRepository extends JpaRepository<Driver, Long> {
}

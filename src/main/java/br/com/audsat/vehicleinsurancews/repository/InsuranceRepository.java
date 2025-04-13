package br.com.audsat.vehicleinsurancews.repository;

import br.com.audsat.vehicleinsurancews.model.Insurance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InsuranceRepository extends JpaRepository<Insurance, Long> {
}

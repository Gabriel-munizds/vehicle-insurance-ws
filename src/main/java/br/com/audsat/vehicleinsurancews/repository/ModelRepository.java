package br.com.audsat.vehicleinsurancews.repository;

import br.com.audsat.vehicleinsurancews.model.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ModelRepository extends JpaRepository<Model, Long> {
    @Query("select m from Model m " +
            "where m.fipeCode = :fipeCodeModelCar ")
    Optional<Model> findByFipeCode(String fipeCodeModelCar);
}

package br.com.audsat.vehicleinsurancews.repository;

import br.com.audsat.vehicleinsurancews.model.Car;
import br.com.audsat.vehicleinsurancews.model.Claim;
import br.com.audsat.vehicleinsurancews.model.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ClaimRepository extends JpaRepository<Claim, Long> {
    @Query("select case when count(c) > 0 then true else false end " +
            "from Claim c " +
            "where c.driver = :driver ")
    Boolean existsByDriver(Driver driver);
    @Query("select case when count(c) > 0 then true else false end " +
            "from Claim c " +
            "where c.car = :car ")
    Boolean existsByCar(Car car);
}

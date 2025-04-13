package br.com.audsat.vehicleinsurancews.service;

import br.com.audsat.vehicleinsurancews.model.Car;
import br.com.audsat.vehicleinsurancews.model.Driver;
import br.com.audsat.vehicleinsurancews.repository.ClaimRepository;
import org.springframework.stereotype.Service;

@Service
public class ClaimService {
    private final ClaimRepository claimRepository;

    public ClaimService(ClaimRepository claimRepository) {
        this.claimRepository = claimRepository;
    }

    public Boolean hasDriverClaims(Driver driver) {
        return claimRepository.existsByDriver(driver);
    }

    public Boolean hasVehicleClaims(Car car) {
        return claimRepository.existsByCar(car);
    }
}

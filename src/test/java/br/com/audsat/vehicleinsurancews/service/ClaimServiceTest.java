package br.com.audsat.vehicleinsurancews.service;

import br.com.audsat.vehicleinsurancews.model.Car;
import br.com.audsat.vehicleinsurancews.model.Driver;
import br.com.audsat.vehicleinsurancews.repository.ClaimRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClaimServiceTest {

    @Mock
    private ClaimRepository claimRepository;

    @InjectMocks
    private ClaimService claimService;

    @Test
    void hasDriverClaims_shouldReturnTrueWhenDriverHasClaims() {
        Driver driver = new Driver();
        driver.setId(1L);
        when(claimRepository.existsByDriver(driver)).thenReturn(true);
        Boolean result = claimService.hasDriverClaims(driver);
        assertTrue(result);
        verify(claimRepository).existsByDriver(driver);
    }

    @Test
    void hasDriverClaims_shouldReturnFalseWhenDriverHasNoClaims() {
        Driver driver = new Driver();
        driver.setId(1L);
        when(claimRepository.existsByDriver(driver)).thenReturn(false);
        Boolean result = claimService.hasDriverClaims(driver);
        assertFalse(result);
        verify(claimRepository).existsByDriver(driver);
    }

    @Test
    void hasVehicleClaims_shouldReturnTrueWhenCarHasClaims() {
        Car car = new Car();
        car.setId(1L);
        when(claimRepository.existsByCar(car)).thenReturn(true);
        Boolean result = claimService.hasVehicleClaims(car);
        assertTrue(result);
        verify(claimRepository).existsByCar(car);
    }

    @Test
    void hasVehicleClaims_shouldReturnFalseWhenCarHasNoClaims() {
        Car car = new Car();
        car.setId(1L);
        when(claimRepository.existsByCar(car)).thenReturn(false);
        Boolean result = claimService.hasVehicleClaims(car);
        assertFalse(result);
        verify(claimRepository).existsByCar(car);
    }
}
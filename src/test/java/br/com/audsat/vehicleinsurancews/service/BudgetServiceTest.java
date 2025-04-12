package br.com.audsat.vehicleinsurancews.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import br.com.audsat.vehicleinsurancews.config.mapper.GenericMapper;
import br.com.audsat.vehicleinsurancews.dto.BudgetDtoIn;
import br.com.audsat.vehicleinsurancews.dto.BudgetDtoOut;
import br.com.audsat.vehicleinsurancews.exception.NotFoundException;
import br.com.audsat.vehicleinsurancews.model.*;
import br.com.audsat.vehicleinsurancews.model.valueobjects.InsuranceValue;
import br.com.audsat.vehicleinsurancews.model.valueobjects.RiskProfile;
import br.com.audsat.vehicleinsurancews.repository.InsuranceRepository;
import br.com.audsat.vehicleinsurancews.utils.PojoFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class BudgetServiceTest {

    @Mock
    private GenericMapper genericMapper;

    @Mock
    private CustomerService customerService;

    @Mock
    private CarService carService;

    @Mock
    private InsuranceRepository insuranceRepository;

    @Mock
    private InsuranceCalculatorService insuranceCalculatorService;

    @Mock
    private ClaimService claimService;

    @InjectMocks
    private BudgetService budgetService;

    private BudgetDtoIn budgetDtoIn;
    private Car car;
    private Customer customer;
    private Driver driver;
    private Insurance insurance;
    private BudgetDtoOut budgetDtoOut;
    private RiskProfile riskProfile;
    private InsuranceValue insuranceValue;

    @BeforeEach
    void setUp() {
        driver = PojoFactory.createValidDriver();
        customer = PojoFactory.createValidCustomer();
        car = PojoFactory.createValidCar();
        insurance = PojoFactory.createValidInsurance();
        budgetDtoIn = PojoFactory.createValidBudgetDtoIn();
        budgetDtoOut = PojoFactory.createValidBudgetDtoOut();
        riskProfile = PojoFactory.createValidRiskProfile();
        insuranceValue = PojoFactory.createValidInsuranceValue();
    }

    @Test
    @DisplayName("Should Create New Budget Successfully")
    void createBudget() {
        when(carService.returnCar(budgetDtoIn)).thenReturn(car);
        when(customerService.returnCustomer(budgetDtoIn, car)).thenReturn(customer);
        when(claimService.hasDriverClaims(customer.getDriver())).thenReturn(false);
        when(claimService.hasVehicleClaims(car)).thenReturn(false);
        when(insuranceCalculatorService.calculateInsuranceValue(
                car.getModel().getFipeValue(), riskProfile))
                .thenReturn(insuranceValue);
        when(insuranceRepository.save(any(Insurance.class))).thenReturn(insurance);
        when(genericMapper.toObject(any(Insurance.class), eq(BudgetDtoOut.class))).thenReturn(budgetDtoOut);

        BudgetDtoOut result = budgetService.createBudget(budgetDtoIn);

        assertNotNull(result);
        assertEquals(budgetDtoOut.getId(), result.getId());
        assertEquals(budgetDtoOut.getInsuranceValue(), result.getInsuranceValue());

        verify(carService).returnCar(budgetDtoIn);
        verify(customerService).returnCustomer(budgetDtoIn, car);
        verify(claimService).hasDriverClaims(customer.getDriver());
        verify(claimService).hasVehicleClaims(car);
        verify(insuranceCalculatorService).calculateInsuranceValue(
                car.getModel().getFipeValue(), riskProfile);
        verify(insuranceRepository).save(any(Insurance.class));
        verify(genericMapper).toObject(any(Insurance.class), eq(BudgetDtoOut.class));
    }

    @Test
    @DisplayName("Should Return Budget When Exists")
    void findBudgetByInsuranceId_CaseSuccess() {
        when(insuranceRepository.findById(1L)).thenReturn(Optional.of(insurance));
        when(genericMapper.toObject(insurance, BudgetDtoOut.class)).thenReturn(budgetDtoOut);

        BudgetDtoOut result = budgetService.findBudgetByInsuranceId(1L);

        assertNotNull(result);
        assertEquals(budgetDtoOut.getId(), result.getId());

        verify(insuranceRepository).findById(1L);
        verify(genericMapper).toObject(insurance, BudgetDtoOut.class);
    }

    @Test
    @DisplayName("Should Throw Not Found Exception When Budget Not Exists")
    void findBudgetByInsuranceId_CaseError() {
        when(insuranceRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> {
            budgetService.findBudgetByInsuranceId(99L);
        });

        verify(insuranceRepository).findById(99L);
    }

    @Test
    @DisplayName("Should Update Existing Budget Successfully")
    void updateBudget_CaseSuccess() {
        BudgetDtoIn updateDto = new BudgetDtoIn();
        updateDto.setCustomerName("John Updated");
        updateDto.setDriverDocument("12345678901");
        updateDto.setLicensePlate("XYZ9A87");
        updateDto.setMainDriver(false);

        InsuranceValue updatedInsuranceValue = new InsuranceValue(
                new BigDecimal("6000.00"), new BigDecimal("4.00"));

        when(insuranceRepository.findById(1L)).thenReturn(Optional.of(insurance));
        when(carService.updateCar(updateDto, insurance.getCar())).thenReturn(insurance.getCar());
        when(customerService.updateCustomer(updateDto, insurance.getCustomer())).thenReturn(insurance.getCustomer());
        when(claimService.hasDriverClaims(insurance.getCustomer().getDriver())).thenReturn(true);
        when(claimService.hasVehicleClaims(insurance.getCar())).thenReturn(false);
        when(insuranceCalculatorService.calculateInsuranceValue(
                insurance.getCar().getModel().getFipeValue(),
                new RiskProfile(driver.getBirthdate(), false, true, false)))
                .thenReturn(updatedInsuranceValue);
        when(insuranceRepository.save(insurance)).thenReturn(insurance);
        when(genericMapper.toObject(insurance, BudgetDtoOut.class)).thenReturn(budgetDtoOut);

        BudgetDtoOut result = budgetService.updateBudget(1L, updateDto);

        assertNotNull(result);
        verify(insuranceRepository).findById(1L);
        verify(carService).updateCar(updateDto, insurance.getCar());
        verify(customerService).updateCustomer(updateDto, insurance.getCustomer());
        verify(insuranceRepository).save(insurance);
        assertNotNull(insurance.getUpdatedAt());
    }

    @Test
    @DisplayName("Should Throw Not Found Exception When Budget Not Exists")
    void updateBudget_CaseError() {
        when(insuranceRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> {
            budgetService.updateBudget(99L, budgetDtoIn);
        });

        verify(insuranceRepository).findById(99L);
    }

    @Test
    @DisplayName("Should Delete Budget When Exists")
    void deleteBudgetByInsuranceId_CaseSuccess() {
        when(insuranceRepository.findById(1L)).thenReturn(Optional.of(insurance));
        doNothing().when(insuranceRepository).delete(insurance);

        budgetService.deleteBudgetByInsuranceId(1L);

        verify(insuranceRepository).findById(1L);
        verify(insuranceRepository).delete(insurance);
    }

    @Test
    @DisplayName("Should Throw Not Found Exception When Budget Not Exists")
    void deleteBudgetByInsuranceId_CaseError() {
        when(insuranceRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> {
            budgetService.deleteBudgetByInsuranceId(99L);
        });

        verify(insuranceRepository).findById(99L);
    }

    @Test
    @DisplayName("Should Return Correct Risk Profile")
    void getRiskProfile() {
        when(claimService.hasDriverClaims(driver)).thenReturn(true);
        when(claimService.hasVehicleClaims(car)).thenReturn(false);

        RiskProfile result = budgetService.getRiskProfile(driver, car, true);

        assertNotNull(result);
        assertEquals(driver.getBirthdate(), result.getBirthdateDriver());
        assertTrue(result.getMainDriver());
        assertTrue(result.getHasDriverClaims());
        assertFalse(result.getHasVehicleClaims());

        verify(claimService).hasDriverClaims(driver);
        verify(claimService).hasVehicleClaims(car);
    }
}
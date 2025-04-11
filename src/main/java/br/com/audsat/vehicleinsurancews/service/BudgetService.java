package br.com.audsat.vehicleinsurancews.service;

import br.com.audsat.vehicleinsurancews.config.mapper.GenericMapper;
import br.com.audsat.vehicleinsurancews.dto.BudgetDtoIn;
import br.com.audsat.vehicleinsurancews.dto.BudgetDtoOut;
import br.com.audsat.vehicleinsurancews.model.Car;
import br.com.audsat.vehicleinsurancews.model.Customer;
import br.com.audsat.vehicleinsurancews.model.Driver;
import br.com.audsat.vehicleinsurancews.model.Insurance;
import br.com.audsat.vehicleinsurancews.model.valueobjects.InsuranceValue;
import br.com.audsat.vehicleinsurancews.model.valueobjects.RiskProfile;
import br.com.audsat.vehicleinsurancews.repository.InsuranceRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class BudgetService {
    private final GenericMapper genericMapper;
    private final CustomerService customerService;
    private final DriverService driverService;
    private final CarService carService;
    private final InsuranceRepository insuranceRepository;
    private final InsuranceCalculatorService insuranceCalculatorService;
    private final ClaimService claimService;

    public BudgetService(GenericMapper genericMapper, CustomerService customerService, DriverService driverService, CarService carService, InsuranceRepository insuranceRepository, InsuranceCalculatorService insuranceCalculatorService, ClaimService claimService) {
        this.genericMapper = genericMapper;
        this.customerService = customerService;
        this.driverService = driverService;
        this.carService = carService;
        this.insuranceRepository = insuranceRepository;
        this.insuranceCalculatorService = insuranceCalculatorService;
        this.claimService = claimService;
    }

    @Transactional
    public BudgetDtoOut createBudget(BudgetDtoIn dto) {
        Car car = carService.createCar(dto);
        Driver driver = driverService.createDriver(dto, car);
        Customer customer = customerService.createCustomer(dto.getCustomer(), driver);
        RiskProfile riskProfile = getRiskProfile(driver, car, dto.getMainDriver());
        InsuranceValue insuranceValue = insuranceCalculatorService.calculateInsuranceValue(car.getFipeValue(), riskProfile);
        Insurance insurance = Insurance.InsuranceBuilder.anInsurance()
                .car(car).creationDate(LocalDateTime.now())
                .customer(customer)
                .insuranceValue(insuranceValue.getValue())
                .aliquot(insuranceValue.getAliquot())
                .isActive(true).build();
        insuranceRepository.save(insurance);
        return genericMapper.toObject(insurance, BudgetDtoOut.class);
    }

    private RiskProfile getRiskProfile(Driver driver, Car car, Boolean mainDriver) {
        Boolean hasDriverClaims = claimService.hasDriverClaims(driver);
        Boolean hasVehicleClaims = claimService.hasVehicleClaims(car);
        return new RiskProfile(driver.getBirthdate(), mainDriver, hasDriverClaims, hasVehicleClaims);
    }

    public BudgetDtoOut findBudgetByInsuranceId(Long insuranceId) {
        return null;
    }

    public BudgetDtoOut updateBudget(Long insuranceId, BudgetDtoIn dto) {
        return null;
    }

    public void deleteBudgetByInsuranceId(Long insuranceId) {
    }
}

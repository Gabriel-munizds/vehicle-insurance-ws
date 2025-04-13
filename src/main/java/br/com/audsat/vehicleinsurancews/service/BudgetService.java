package br.com.audsat.vehicleinsurancews.service;

import br.com.audsat.vehicleinsurancews.config.mapper.GenericMapper;
import br.com.audsat.vehicleinsurancews.dto.BudgetDtoIn;
import br.com.audsat.vehicleinsurancews.dto.BudgetDtoOut;
import br.com.audsat.vehicleinsurancews.exception.NotFoundException;
import br.com.audsat.vehicleinsurancews.model.Car;
import br.com.audsat.vehicleinsurancews.model.Customer;
import br.com.audsat.vehicleinsurancews.model.Driver;
import br.com.audsat.vehicleinsurancews.model.Insurance;
import br.com.audsat.vehicleinsurancews.model.valueobjects.InsuranceValue;
import br.com.audsat.vehicleinsurancews.model.valueobjects.RiskProfile;
import br.com.audsat.vehicleinsurancews.repository.InsuranceRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class BudgetService {
    private final GenericMapper genericMapper;
    private final CustomerService customerService;
    private final CarService carService;
    private final InsuranceRepository insuranceRepository;
    private final InsuranceCalculatorService insuranceCalculatorService;
    private final ClaimService claimService;

    public BudgetService(GenericMapper genericMapper, CustomerService customerService, CarService carService, InsuranceRepository insuranceRepository, InsuranceCalculatorService insuranceCalculatorService, ClaimService claimService) {
        this.genericMapper = genericMapper;
        this.customerService = customerService;
        this.carService = carService;
        this.insuranceRepository = insuranceRepository;
        this.insuranceCalculatorService = insuranceCalculatorService;
        this.claimService = claimService;
    }

    @Transactional
    public BudgetDtoOut createBudget(BudgetDtoIn dto) {
        Car car = carService.returnCar(dto);
        Customer customer = customerService.returnCustomer(dto, car);
        RiskProfile riskProfile = getRiskProfile(customer.getDriver(), car, dto.getMainDriver());
        InsuranceValue insuranceValue = insuranceCalculatorService.calculateInsuranceValue(car.getModel().getFipeValue(), riskProfile);
        Insurance insurance = Insurance.InsuranceBuilder.anInsurance()
                .car(car).creationDate(LocalDateTime.now())
                .customer(customer)
                .insuranceValue(insuranceValue.getValue())
                .aliquot(insuranceValue.getAliquot())
                .isActive(true).build();
        insuranceRepository.save(insurance);
        return genericMapper.toObject(insurance, BudgetDtoOut.class);
    }

    protected RiskProfile getRiskProfile(Driver driver, Car car, Boolean mainDriver) {
        Boolean hasDriverClaims = claimService.hasDriverClaims(driver);
        Boolean hasVehicleClaims = claimService.hasVehicleClaims(car);
        return new RiskProfile(driver.getBirthdate(), mainDriver, hasDriverClaims, hasVehicleClaims);
    }

    public BudgetDtoOut findBudgetByInsuranceId(Long insuranceId) {
        Insurance insurance = insuranceRepository.findById(insuranceId).orElseThrow(() -> new NotFoundException("Budget not found! "));
        return genericMapper.toObject(insurance, BudgetDtoOut.class);
    }

    @Transactional
    public BudgetDtoOut updateBudget(Long insuranceId, BudgetDtoIn dto) {
        Insurance target = insuranceRepository.findById(insuranceId).orElseThrow(() -> new NotFoundException("Budget not found! "));
        Car car = carService.updateCar(dto, target.getCar());
        Customer customer = customerService.updateCustomer(dto, target.getCustomer());
        RiskProfile riskProfile = getRiskProfile(customer.getDriver(), car, dto.getMainDriver());
        InsuranceValue insuranceValue = insuranceCalculatorService.calculateInsuranceValue(car.getModel().getFipeValue(), riskProfile);
        Insurance source = Insurance.InsuranceBuilder.anInsurance()
                .car(car).customer(customer)
                .insuranceValue(insuranceValue.getValue())
                .aliquot(insuranceValue.getAliquot())
                .isActive(true).build();
        BeanUtils.copyProperties(source, target, "id", "creationDate");
        target.setUpdatedAt(LocalDateTime.now());
        insuranceRepository.save(target);
        return genericMapper.toObject(target, BudgetDtoOut.class);
    }

    @Transactional
    public void deleteBudgetByInsuranceId(Long insuranceId) {
        Insurance insurance = insuranceRepository.findById(insuranceId).orElseThrow(() -> new NotFoundException("Budget not found! "));
        insuranceRepository.delete(insurance);
    }
}

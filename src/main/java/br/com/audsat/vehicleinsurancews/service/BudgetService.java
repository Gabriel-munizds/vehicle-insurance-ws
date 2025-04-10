package br.com.audsat.vehicleinsurancews.service;

import br.com.audsat.vehicleinsurancews.config.mapper.GenericMapper;
import br.com.audsat.vehicleinsurancews.dto.BudgetDtoIn;
import br.com.audsat.vehicleinsurancews.dto.BudgetDtoOut;
import br.com.audsat.vehicleinsurancews.model.Car;
import br.com.audsat.vehicleinsurancews.model.Customer;
import br.com.audsat.vehicleinsurancews.model.Driver;
import br.com.audsat.vehicleinsurancews.model.Insurance;
import br.com.audsat.vehicleinsurancews.repository.InsuranceRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
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

    public BudgetService(GenericMapper genericMapper, CustomerService customerService, DriverService driverService, CarService carService, InsuranceRepository insuranceRepository) {
        this.genericMapper = genericMapper;
        this.customerService = customerService;
        this.driverService = driverService;
        this.carService = carService;
        this.insuranceRepository = insuranceRepository;
    }

    @Transactional
    public BudgetDtoOut createBudget(BudgetDtoIn dto) {
        Car car = carService.createCar(dto);
        Driver driver = driverService.createDriver(dto, car);
        Customer customer = customerService.createCustomer(dto.getCustomer(), driver);
        Insurance insurance = Insurance.InsuranceBuilder.anInsurance()
                .car(car).creationDate(LocalDateTime.now())
                .customer(customer).isActive(true).build();
        insuranceRepository.save(insurance);
        return genericMapper.toObject(insurance, BudgetDtoOut.class);
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

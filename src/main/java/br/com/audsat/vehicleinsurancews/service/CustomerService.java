package br.com.audsat.vehicleinsurancews.service;

import br.com.audsat.vehicleinsurancews.config.mapper.GenericMapper;
import br.com.audsat.vehicleinsurancews.dto.BudgetDtoIn;
import br.com.audsat.vehicleinsurancews.model.Car;
import br.com.audsat.vehicleinsurancews.model.CarDriver;
import br.com.audsat.vehicleinsurancews.model.Customer;
import br.com.audsat.vehicleinsurancews.model.Driver;
import br.com.audsat.vehicleinsurancews.repository.CarDriverRepository;
import br.com.audsat.vehicleinsurancews.repository.CustomerRepository;
import br.com.audsat.vehicleinsurancews.repository.DriverRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final DriverRepository driverRepository;
    private final CarDriverRepository carDriverRepository;
    private final GenericMapper genericMapper;

    public CustomerService(CustomerRepository customerRepository, DriverRepository driverRepository, CarDriverRepository carDriverRepository, GenericMapper genericMapper) {
        this.customerRepository = customerRepository;
        this.driverRepository = driverRepository;
        this.carDriverRepository = carDriverRepository;
        this.genericMapper = genericMapper;
    }

    private Customer createCustomer(BudgetDtoIn dto, Car car) {
        Driver driver = createDriver(dto, car);
        Customer customer = genericMapper.toObject(dto, Customer.class);
        customer.setDriver(driver);
        customerRepository.save(customer);
        return customer;
    }

    protected Customer returnCustomer(BudgetDtoIn dto, Car car) {
        Optional<Customer> customer = customerRepository.findByDriverDocument(dto.getDriverDocument());
        return customer.orElseGet(() -> createCustomer(dto, car));
    }

    private Driver createDriver(BudgetDtoIn dto, Car car) {
        Driver driver = genericMapper.toObject(dto, Driver.class);
        driverRepository.save(driver);
        CarDriver carDriver = new CarDriver(car, driver, dto.getMainDriver());
        carDriverRepository.save(carDriver);
        return driver;
    }

    protected Customer updateCustomer(BudgetDtoIn dto, Customer target) {
        updateDriver(dto, target.getDriver());
        Customer source = genericMapper.toObject(dto, Customer.class);
        BeanUtils.copyProperties(source, target, "id", "driver");
        customerRepository.save(target);
        return target;
    }

    private void updateDriver(BudgetDtoIn dto, Driver target) {
        Driver source = genericMapper.toObject(dto, Driver.class);
        BeanUtils.copyProperties(source, target, "id");
        driverRepository.save(target);
    }
}

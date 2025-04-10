package br.com.audsat.vehicleinsurancews.service;

import br.com.audsat.vehicleinsurancews.config.mapper.GenericMapper;
import br.com.audsat.vehicleinsurancews.dto.BudgetDtoIn;
import br.com.audsat.vehicleinsurancews.dto.CustomerDtoIn;
import br.com.audsat.vehicleinsurancews.model.Customer;
import br.com.audsat.vehicleinsurancews.model.Driver;
import br.com.audsat.vehicleinsurancews.repository.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final GenericMapper genericMapper;

    public CustomerService(CustomerRepository customerRepository, GenericMapper genericMapper) {
        this.customerRepository = customerRepository;
        this.genericMapper = genericMapper;
    }

    protected Customer createCustomer(CustomerDtoIn dto, Driver driver) {
        Customer customer = genericMapper.toObject(dto, Customer.class);
        customer.setDriver(driver);
        customerRepository.save(customer);
        return customer;
    }
}

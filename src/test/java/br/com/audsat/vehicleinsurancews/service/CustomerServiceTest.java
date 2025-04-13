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
import br.com.audsat.vehicleinsurancews.useful.PojoFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private DriverRepository driverRepository;

    @Mock
    private CarDriverRepository carDriverRepository;
    @Mock
    private GenericMapper genericMapper;

    @InjectMocks
    private CustomerService customerService;

    @Test
    void returnCustomer_shouldReturnExistingCustomer() {
        BudgetDtoIn dto = PojoFactory.createValidBudgetDtoIn();
        Car car = PojoFactory.createValidCar();
        Customer existingCustomer = PojoFactory.createValidCustomer();
        when(customerRepository.findByDriverDocument("12345678901"))
                .thenReturn(Optional.of(existingCustomer));
        Customer result = customerService.returnCustomer(dto, car);
        assertSame(existingCustomer, result);
        verify(customerRepository).findByDriverDocument("12345678901");
        verifyNoInteractions(driverRepository, carDriverRepository, genericMapper);
    }

    @Test
    void returnCustomer_shouldCreateNewCustomerWhenNotFound() {
        BudgetDtoIn dto = PojoFactory.createValidBudgetDtoIn();
        Car car = PojoFactory.createValidCar();
        Customer newCustomer = PojoFactory.createValidCustomer();

        when(customerRepository.findByDriverDocument("12345678901"))
                .thenReturn(Optional.empty());
        when(genericMapper.toObject(dto, Customer.class))
                .thenReturn(newCustomer);
        when(genericMapper.toObject(dto, Driver.class))
                .thenReturn(newCustomer.getDriver());
        when(customerRepository.save(newCustomer))
                .thenReturn(newCustomer);
        when(driverRepository.save(newCustomer.getDriver()))
                .thenReturn(newCustomer.getDriver());
        when(carDriverRepository.save(any(CarDriver.class)))
                .thenReturn(new CarDriver());

        Customer result = customerService.returnCustomer(dto, car);

        assertSame(newCustomer, result);
        verify(customerRepository).findByDriverDocument("12345678901");
        verify(genericMapper).toObject(dto, Customer.class);
        verify(genericMapper).toObject(dto, Driver.class);
        verify(driverRepository).save(newCustomer.getDriver());
        verify(carDriverRepository).save(any(CarDriver.class));
        verify(customerRepository).save(newCustomer);
    }

    @Test
    void updateCustomer_shouldUpdateCustomerAndDriver() {
        BudgetDtoIn dto = PojoFactory.createValidBudgetDtoIn();
        dto.setCustomerName("Updated Name");

        Customer target = PojoFactory.createValidCustomer();

        Customer source = PojoFactory.createValidCustomer();
        source.setName("Updated Name");

        when(genericMapper.toObject(eq(dto), eq(Customer.class)))
                .thenReturn(source);
        when(genericMapper.toObject(eq(dto), eq(Driver.class)))
                .thenReturn(source.getDriver());
        when(customerRepository.save(target))
                .thenReturn(target);
        when(driverRepository.save(target.getDriver()))
                .thenReturn(target.getDriver());

        Customer result = customerService.updateCustomer(dto, target);

        assertSame(target, result);
        assertEquals("Updated Name", target.getName());
        assertEquals(1L, target.getId());
        verify(genericMapper).toObject(dto, Customer.class);
        verify(driverRepository).save(target.getDriver());
        verify(customerRepository).save(target);
    }
}
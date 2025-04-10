package br.com.audsat.vehicleinsurancews.service;

import br.com.audsat.vehicleinsurancews.config.mapper.GenericMapper;
import br.com.audsat.vehicleinsurancews.dto.BudgetDtoIn;
import br.com.audsat.vehicleinsurancews.dto.BudgetDtoOut;
import br.com.audsat.vehicleinsurancews.dto.CustomerDtoIn;
import br.com.audsat.vehicleinsurancews.model.Car;
import br.com.audsat.vehicleinsurancews.model.Customer;
import br.com.audsat.vehicleinsurancews.model.Driver;
import br.com.audsat.vehicleinsurancews.model.Insurance;
import br.com.audsat.vehicleinsurancews.repository.InsuranceRepository;
import br.com.audsat.vehicleinsurancews.utils.PojoFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class BudgetServiceTest {
    @Mock
    private CarService carService;

    @Mock
    private DriverService driverService;

    @Mock
    private CustomerService customerService;

    @Mock
    private InsuranceRepository insuranceRepository;

    @Mock
    private GenericMapper genericMapper;
    @InjectMocks
    private BudgetService budgetService;

    @Test
    void createBudget_shouldReturnBudgetDtoOutWhenValidInput() {
        BudgetDtoIn dto = PojoFactory.createValidBudgetDtoIn();
        Car mockCar = new Car();
        Driver mockDriver = new Driver();
        Customer mockCustomer = new Customer();
        Insurance mockInsurance = new Insurance();
        BudgetDtoOut mockDtoOut = new BudgetDtoOut();

        when(carService.createCar(any(BudgetDtoIn.class))).thenReturn(mockCar);
        when(driverService.createDriver(any(BudgetDtoIn.class), any(Car.class))).thenReturn(mockDriver);
        when(customerService.createCustomer(any(CustomerDtoIn.class), any(Driver.class))).thenReturn(mockCustomer);
        when(insuranceRepository.save(any(Insurance.class))).thenReturn(mockInsurance);
        when(genericMapper.toObject(any(Insurance.class), eq(BudgetDtoOut.class))).thenReturn(mockDtoOut);

        BudgetDtoOut result = budgetService.createBudget(dto);

        assertNotNull(result);

        verify(carService, times(1)).createCar(dto);
        verify(driverService, times(1)).createDriver(dto, mockCar);
        verify(customerService, times(1)).createCustomer(dto.getCustomer(), mockDriver);
        verify(insuranceRepository, times(1)).save(any(Insurance.class));

        ArgumentCaptor<Insurance> insuranceCaptor = ArgumentCaptor.forClass(Insurance.class);
        verify(insuranceRepository).save(insuranceCaptor.capture());

        Insurance savedInsurance = insuranceCaptor.getValue();
        assertEquals(mockCar, savedInsurance.getCar());
        assertEquals(mockCustomer, savedInsurance.getCustomer());
        assertTrue(savedInsurance.isActive());
        assertNotNull(savedInsurance.getCreationDate());
    }

    @Test
    void createBudget_shouldThrowExceptionWhenInvalidInput() {
    }

    @Test
    void findBudgetByInsuranceId_shouldReturnBudgetDtoOutWhenInsuranceIdExists() {
    }

    @Test
    void findBudgetByInsuranceId_shouldThrowNotFoundExceptionWhenInsuranceIdDoesNotExist() {
    }

    @Test
    void updateBudget_shouldReturnUpdatedBudgetDtoOutWhenValidInput() {
    }

    @Test
    void updateBudget_shouldThrowNotFoundExceptionWhenInsuranceIdDoesNotExist() {
    }

    @Test
    void deleteBudgetByInsuranceId_shouldDeleteBudgetWhenInsuranceIdExists() {
    }

    @Test
    void deleteBudgetByInsuranceId_shouldThrowNotFoundExceptionWhenInsuranceIdDoesNotExist() {
    }
}

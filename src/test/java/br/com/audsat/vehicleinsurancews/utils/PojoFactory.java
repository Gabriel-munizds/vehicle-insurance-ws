package br.com.audsat.vehicleinsurancews.utils;

import br.com.audsat.vehicleinsurancews.dto.BudgetDtoIn;
import br.com.audsat.vehicleinsurancews.dto.CustomerDtoIn;

import java.math.BigDecimal;
import java.time.LocalDate;

public class PojoFactory {

    public static BudgetDtoIn createValidBudgetDtoIn() {
        BudgetDtoIn dto = new BudgetDtoIn();
        dto.setDriverName("John Doe");
        dto.setDriverDocument("12345678901");
        dto.setMainDriver(true);
        dto.setBirthdateDriver(LocalDate.of(1980, 1, 1));
        dto.setModelCar("Golf");
        dto.setManufacturerCar("Volkswagen");
        dto.setModelYearCar(2022);
        dto.setFipeValueCar(new BigDecimal("120000.00"));

        CustomerDtoIn customerDto = new CustomerDtoIn();
        customerDto.setCustomerName("Jane Doe");
        dto.setCustomer(customerDto);
        return dto;
    }
}

package br.com.audsat.vehicleinsurancews.utils;

import br.com.audsat.vehicleinsurancews.dto.BudgetDtoIn;

import java.math.BigDecimal;
import java.time.LocalDate;

public class PojoFactory {

    public static BudgetDtoIn createValidBudgetDtoIn() {
        BudgetDtoIn dto = new BudgetDtoIn();
        dto.setCustomerName("John Doe");
        dto.setDriverDocument("12345678901");
        dto.setMainDriver(true);
        dto.setBirthdateDriver(LocalDate.of(1980, 1, 1));
        dto.setModelCar("Golf");
        dto.setManufacturerModelCar("Volkswagen");
        dto.setModelYearCar(2022);
        dto.setFipeValueModelCar(new BigDecimal("120000.00"));
        return dto;
    }
}

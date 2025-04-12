package br.com.audsat.vehicleinsurancews.utils;

import br.com.audsat.vehicleinsurancews.dto.BudgetDtoIn;
import br.com.audsat.vehicleinsurancews.dto.BudgetDtoOut;
import br.com.audsat.vehicleinsurancews.model.*;
import br.com.audsat.vehicleinsurancews.model.valueobjects.InsuranceValue;
import br.com.audsat.vehicleinsurancews.model.valueobjects.RiskProfile;

import java.math.BigDecimal;
import java.time.LocalDate;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class PojoFactory {

    public static BudgetDtoIn createValidBudgetDtoIn() {
        BudgetDtoIn dto = new BudgetDtoIn();
        dto.setCustomerName("John Doe");
        dto.setDriverDocument("12345678901");
        dto.setLicensePlate("ABC1D23");
        dto.setMainDriver(true);
        dto.setBirthdateDriver(LocalDate.of(1985, 5, 15));
        dto.setModelCar("Corolla");
        dto.setManufacturerModelCar("Toyota");
        dto.setModelYearCar(2023);
        dto.setFipeCodeModelCar("12345678");
        dto.setFipeValueModelCar(new BigDecimal("150000.00"));
        return dto;
    }

    public static BudgetDtoOut createValidBudgetDtoOut() {
        BudgetDtoOut dto = new BudgetDtoOut();
        dto.setId(1L);
        dto.setInsuranceValue(new BigDecimal("5000.00"));
        dto.setAliquot(new BigDecimal("3.33"));
        return dto;
    }

    public static Driver createValidDriver() {
        Driver driver = new Driver();
        driver.setId(1L);
        driver.setBirthdate(LocalDate.of(1985, 5, 15));
        driver.setDocument("12345678901");
        return driver;
    }

    public static Customer createValidCustomer() {
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setName("John Doe");
        customer.setDriver(createValidDriver());
        return customer;
    }

    public static Model createValidModel() {
        Model model = new Model();
        model.setId(1L);
        model.setFipeCode("001001-1");
        model.setDescription("Corolla");
        model.setManufacturer("Toyota");
        model.setFuelType(1);
        model.setModelYear(2023);
        model.setFipeValue(new BigDecimal("150000.00"));
        return model;
    }

    public static Car createValidCar() {
        Car car = new Car();
        car.setId(1L);
        car.setModel(createValidModel());
        car.setLicensePlate("ABC1D23");
        return car;
    }

    public static Insurance createValidInsurance() {
        Insurance insurance = new Insurance();
        insurance.setId(1L);
        insurance.setCar(createValidCar());
        insurance.setCustomer(createValidCustomer());
        insurance.setCreationDate(LocalDateTime.now());
        insurance.setInsuranceValue(new BigDecimal("5000.00"));
        insurance.setAliquot(new BigDecimal("3.33"));
        insurance.setActive(true);
        return insurance;
    }

    public static RiskProfile createValidRiskProfile() {
        return new RiskProfile(
                LocalDate.of(1985, 5, 15),
                true,
                false,
                false
        );
    }

    public static InsuranceValue createValidInsuranceValue() {
        return new InsuranceValue(
                new BigDecimal("5000.00"),
                new BigDecimal("3.33")
        );
    }

    public static RiskProfile createRiskProfileWithYoungDriverRisk() {
        return new RiskProfile(
                LocalDate.now().minusYears(20),
                true,
                false,
                false
        );
    }
    public static RiskProfile createRiskProfileWithYoungDriverRiskButIsNotMainDriver() {
        return new RiskProfile(
                LocalDate.now().minusYears(20),
                false,
                false,
                false
        );
    }

    public static RiskProfile createRiskProfileWithYoungDriverRiskAndDriverClaimsRisk() {
        return new RiskProfile(
                LocalDate.now().minusYears(20),
                true,
                true,
                false
        );
    }

    public static RiskProfile createRiskProfileWithYoungDriverRiskAndDriverClaimsRiskAndVehicleClaimsRisk() {
        return new RiskProfile(
                LocalDate.now().minusYears(20),
                true,
                true,
                true
        );
    }


}

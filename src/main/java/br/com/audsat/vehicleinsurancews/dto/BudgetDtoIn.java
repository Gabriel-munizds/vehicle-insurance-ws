package br.com.audsat.vehicleinsurancews.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

public class BudgetDtoIn {
    private CustomerDtoIn customer;
    private String driverName;
    private String driverDocument;
    private Boolean isMainDriver;
    @JsonFormat(pattern = "dd/MM/yyyy", shape=JsonFormat.Shape.STRING, locale = "pt-BR", timezone = "America/Fortaleza")
    private LocalDate birthdateDriver;
    private String modelCar;
    private String manufacturerCar;
    private Integer modelYearCar;
    private BigDecimal fipeValueCar;

    public BudgetDtoIn() {
    }

    public CustomerDtoIn getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDtoIn customer) {
        this.customer = customer;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getDriverDocument() {
        return driverDocument;
    }

    public void setDriverDocument(String driverDocument) {
        this.driverDocument = driverDocument;
    }

    public Boolean getMainDriver() {
        return isMainDriver;
    }

    public void setMainDriver(Boolean mainDriver) {
        isMainDriver = mainDriver;
    }

    public LocalDate getBirthdateDriver() {
        return birthdateDriver;
    }

    public void setBirthdateDriver(LocalDate birthdateDriver) {
        this.birthdateDriver = birthdateDriver;
    }

    public String getModelCar() {
        return modelCar;
    }

    public void setModelCar(String modelCar) {
        this.modelCar = modelCar;
    }

    public String getManufacturerCar() {
        return manufacturerCar;
    }

    public void setManufacturerCar(String manufacturerCar) {
        this.manufacturerCar = manufacturerCar;
    }

    public Integer getModelYearCar() {
        return modelYearCar;
    }

    public void setModelYearCar(Integer modelYearCar) {
        this.modelYearCar = modelYearCar;
    }

    public BigDecimal getFipeValueCar() {
        return fipeValueCar;
    }

    public void setFipeValueCar(BigDecimal fipeValueCar) {
        this.fipeValueCar = fipeValueCar;
    }
}

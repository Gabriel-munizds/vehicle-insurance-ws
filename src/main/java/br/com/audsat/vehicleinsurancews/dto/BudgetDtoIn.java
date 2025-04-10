package br.com.audsat.vehicleinsurancews.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

public class BudgetDtoIn {
    private String nameDriver;
    private String documentDriver;
    private boolean isMainDriver;
    @JsonFormat(pattern = "dd/MM/yyyy", shape=JsonFormat.Shape.STRING, locale = "pt-BR", timezone = "America/Fortaleza")
    private LocalDate birthdateDriver;
    private String modelCar;
    private String manufacturerCar;
    private Integer modelYearCar;
    private BigDecimal fipeValueCar;

    public BudgetDtoIn() {
    }

    public String getNameDriver() {
        return nameDriver;
    }

    public void setNameDriver(String nameDriver) {
        this.nameDriver = nameDriver;
    }

    public String getDocumentDriver() {
        return documentDriver;
    }

    public void setDocumentDriver(String documentDriver) {
        this.documentDriver = documentDriver;
    }

    public boolean isMainDriver() {
        return isMainDriver;
    }

    public void setMainDriver(boolean mainDriver) {
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

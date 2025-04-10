package br.com.audsat.vehicleinsurancews.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

public class BudgetDtoOut {
    private Long id;
    private Long idDriver;
    private String nameDriver;
    private String documentDriver;
    private boolean isMainDriver;
    @JsonFormat(pattern = "dd/MM/yyyy", shape=JsonFormat.Shape.STRING, locale = "pt-BR", timezone = "America/Fortaleza")
    private LocalDate birthdateDriver;
    private Long idCar;
    private String modelCar;
    private String manufacturerCar;
    private Integer modelYearCar;
    private BigDecimal fipeValueCar;
    private BigDecimal aliquot;
    private BigDecimal budgetValue;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdDriver() {
        return idDriver;
    }

    public void setIdDriver(Long idDriver) {
        this.idDriver = idDriver;
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

    public Long getIdCar() {
        return idCar;
    }

    public void setIdCar(Long idCar) {
        this.idCar = idCar;
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

    public BigDecimal getAliquot() {
        return aliquot;
    }

    public void setAliquot(BigDecimal aliquot) {
        this.aliquot = aliquot;
    }

    public BigDecimal getBudgetValue() {
        return budgetValue;
    }

    public void setBudgetValue(BigDecimal budgetValue) {
        this.budgetValue = budgetValue;
    }
}

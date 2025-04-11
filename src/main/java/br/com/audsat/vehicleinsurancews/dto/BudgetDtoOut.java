package br.com.audsat.vehicleinsurancews.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class BudgetDtoOut {
    private Long id;
    private String nameDriver;
    private String documentDriver;
    private Boolean isMainDriver;
    @JsonFormat(pattern = "dd/MM/yyyy", shape=JsonFormat.Shape.STRING, locale = "pt-BR", timezone = "America/Fortaleza")
    private LocalDate birthdateDriver;
    private String modelCar;
    private String manufacturerCar;
    private Integer modelYearCar;
    private BigDecimal fipeValueCar;
    private BigDecimal aliquot;
    private BigDecimal insuranceValue;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss", shape=JsonFormat.Shape.STRING, locale = "pt-BR", timezone = "America/Fortaleza")
    private LocalDateTime creationDate;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss", shape=JsonFormat.Shape.STRING, locale = "pt-BR", timezone = "America/Fortaleza")
    private LocalDateTime updatedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public BigDecimal getAliquot() {
        return aliquot;
    }

    public void setAliquot(BigDecimal aliquot) {
        this.aliquot = aliquot;
    }

    public BigDecimal getInsuranceValue() {
        return insuranceValue;
    }

    public void setInsuranceValue(BigDecimal insuranceValue) {
        this.insuranceValue = insuranceValue;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}

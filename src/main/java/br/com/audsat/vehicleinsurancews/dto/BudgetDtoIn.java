package br.com.audsat.vehicleinsurancews.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

public class BudgetDtoIn {
    @Schema(description = "Nome do cliente", example = "Ricardo Luiz Almada")
    @NotBlank
    private String customerName;
    @Schema(description = "Número do documento de habilitação do motorista", example = "68416487272")
    @NotBlank
    private String driverDocument;
    @Schema(description = "Flag para identificar se este motorista é o motorista principal do veículo", example = "true")
    @NotNull
    private Boolean isMainDriver;
    @Schema(description = "Data de nascimento do motorista", example = "15/03/1993")
    @JsonFormat(pattern = "dd/MM/yyyy", shape=JsonFormat.Shape.STRING, locale = "pt-BR", timezone = "America/Fortaleza")
    @NotNull
    private LocalDate birthdateDriver;
    @Schema(description = "Placa do veículo", example = "AAA1A11")
    @NotBlank
    private String licensePlate;
    @Schema(description = "Descrição do modelo do veículo", example = "Palio 1.0/ Trofeo 1.0 Fire/ Fire Flex 4p")
    @NotBlank
    private String modelCar;
    @Schema(description = "código fipe da marca/modelo do veículo", example = "0011770")
    @NotBlank
    private String fipeCodeModelCar;
    @Schema(description = "Fabricante do modelo do veículo", example = "Fiat")
    @NotBlank
    private String manufacturerModelCar;
    @Schema(description = "Ano do modelo", example = "2017")
    @NotNull
    private Integer modelYearCar;
    @Schema(description = "código do tipo de combustível do veículo. 1 - Gasolina; 2 - Álcool, 3 - Diesel", example = "1")
    @NotNull
    private Integer fuelType;
    @Schema(description = "Valor Fipe atual do veículo. (utilizado somente em caso de não existência do modelo na base de dados)", example = "33184")
    @NotNull
    private BigDecimal fipeValueModelCar;

    public BudgetDtoIn() {
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
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

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getModelCar() {
        return modelCar;
    }

    public void setModelCar(String modelCar) {
        this.modelCar = modelCar;
    }

    public String getFipeCodeModelCar() {
        return fipeCodeModelCar;
    }

    public void setFipeCodeModelCar(String fipeCodeModelCar) {
        this.fipeCodeModelCar = fipeCodeModelCar;
    }

    public String getManufacturerModelCar() {
        return manufacturerModelCar;
    }

    public void setManufacturerModelCar(String manufacturerModelCar) {
        this.manufacturerModelCar = manufacturerModelCar;
    }

    public Integer getModelYearCar() {
        return modelYearCar;
    }

    public void setModelYearCar(Integer modelYearCar) {
        this.modelYearCar = modelYearCar;
    }

    public Integer getFuelType() {
        return fuelType;
    }

    public void setFuelType(Integer fuelType) {
        this.fuelType = fuelType;
    }

    public BigDecimal getFipeValueModelCar() {
        return fipeValueModelCar;
    }

    public void setFipeValueModelCar(BigDecimal fipeValueModelCar) {
        this.fipeValueModelCar = fipeValueModelCar;
    }
}

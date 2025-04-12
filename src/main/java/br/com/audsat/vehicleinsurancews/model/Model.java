package br.com.audsat.vehicleinsurancews.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "models")
public class Model {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "fipe_code", length = 10, nullable = false, unique = true)
    private String fipeCode;
    @Column(length = 100, nullable = false)
    private String description;
    @Column(name = "manufacturer", length = 100, nullable = false)
    private String manufacturer;
    @Column(name = "fuel_type", nullable = false)
    private Integer fuelType;
    @Column(name = "model_year", nullable = false)
    private Integer modelYear;
    @Column(name = "fipe_value", precision = 10, scale = 2, nullable = false)
    private BigDecimal fipeValue;
    @Column(name = "last_update_fipe_value", nullable = false)
    private LocalDateTime lastUpdateFipeValue;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFipeCode() {
        return fipeCode;
    }

    public void setFipeCode(String fipeCode) {
        this.fipeCode = fipeCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Integer getFuelType() {
        return fuelType;
    }

    public void setFuelType(Integer fuelType) {
        this.fuelType = fuelType;
    }

    public Integer getModelYear() {
        return modelYear;
    }

    public void setModelYear(Integer modelYear) {
        this.modelYear = modelYear;
    }

    public BigDecimal getFipeValue() {
        return fipeValue;
    }

    public void setFipeValue(BigDecimal fipeValue) {
        this.fipeValue = fipeValue;
    }

    public LocalDateTime getLastUpdateFipeValue() {
        return lastUpdateFipeValue;
    }

    public void setLastUpdateFipeValue(LocalDateTime lastUpdateFipeValue) {
        this.lastUpdateFipeValue = lastUpdateFipeValue;
    }
}

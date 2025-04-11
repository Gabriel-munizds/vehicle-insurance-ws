package br.com.audsat.vehicleinsurancews.model;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "insurances")
public class Insurance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @Column(name = "creation_dt", nullable = false)
    private LocalDateTime creationDate;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "car_id", nullable = false)
    private Car car;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive;
    @Column(name = "aliquot")
    private BigDecimal aliquot;
    @Column(name = "insurance_value")
    private BigDecimal insuranceValue;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
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

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Boolean isActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public BigDecimal getInsuranceValue() {
        return insuranceValue;
    }

    public void setInsuranceValue(BigDecimal insuranceValue) {
        this.insuranceValue = insuranceValue;
    }

    public BigDecimal getAliquot() {
        return aliquot;
    }

    public void setAliquot(BigDecimal aliquot) {
        this.aliquot = aliquot;
    }

    public static final class InsuranceBuilder {
        private Long id;
        private Customer customer;
        private LocalDateTime creationDate;
        private LocalDateTime updatedAt;
        private Car car;
        private Boolean isActive;
        private BigDecimal aliquot;
        private BigDecimal insuranceValue;

        private InsuranceBuilder() {
        }

        public static InsuranceBuilder anInsurance() {
            return new InsuranceBuilder();
        }

        public InsuranceBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public InsuranceBuilder customer(Customer customer) {
            this.customer = customer;
            return this;
        }

        public InsuranceBuilder creationDate(LocalDateTime creationDate) {
            this.creationDate = creationDate;
            return this;
        }

        public InsuranceBuilder updatedAt(LocalDateTime updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public InsuranceBuilder car(Car car) {
            this.car = car;
            return this;
        }

        public InsuranceBuilder isActive(Boolean isActive) {
            this.isActive = isActive;
            return this;
        }

        public InsuranceBuilder aliquot(BigDecimal aliquot) {
            this.aliquot = aliquot;
            return this;
        }

        public InsuranceBuilder insuranceValue(BigDecimal insuranceValue) {
            this.insuranceValue = insuranceValue;
            return this;
        }

        public Insurance build() {
            Insurance insurance = new Insurance();
            insurance.setId(id);
            insurance.setCustomer(customer);
            insurance.setCreationDate(creationDate);
            insurance.setUpdatedAt(updatedAt);
            insurance.setCar(car);
            insurance.setAliquot(aliquot);
            insurance.setInsuranceValue(insuranceValue);
            insurance.isActive = this.isActive;
            return insurance;
        }
    }
}

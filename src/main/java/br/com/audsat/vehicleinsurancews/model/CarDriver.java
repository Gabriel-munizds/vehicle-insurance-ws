package br.com.audsat.vehicleinsurancews.model;

import jakarta.persistence.*;

@Entity
@Table(name = "car_drivers")
public class CarDriver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "car_id", nullable = false)
    private Car car;

    @ManyToOne
    @JoinColumn(name = "driver_id", nullable = false)
    private Driver driver;

    @Column(name = "is_main_driver", nullable = false)
    private boolean isMainDriver;

    public CarDriver() {
    }

    public CarDriver(Car car, Driver driver, boolean isMainDriver) {
        this.car = car;
        this.driver = driver;
        this.isMainDriver = isMainDriver;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public boolean isMainDriver() {
        return isMainDriver;
    }

    public void setMainDriver(boolean mainDriver) {
        isMainDriver = mainDriver;
    }
}

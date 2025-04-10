package br.com.audsat.vehicleinsurancews.service;

import br.com.audsat.vehicleinsurancews.config.mapper.GenericMapper;
import br.com.audsat.vehicleinsurancews.dto.BudgetDtoIn;
import br.com.audsat.vehicleinsurancews.model.Car;
import br.com.audsat.vehicleinsurancews.model.CarDriver;
import br.com.audsat.vehicleinsurancews.model.Driver;
import br.com.audsat.vehicleinsurancews.repository.CarDriverRepository;
import br.com.audsat.vehicleinsurancews.repository.DriverRepository;
import org.springframework.stereotype.Service;

@Service
public class DriverService {
    private final DriverRepository driverRepository;
    private final CarDriverRepository carDriverRepository;
    private final GenericMapper genericMapper;
    public DriverService(DriverRepository driverRepository, CarDriverRepository carDriverRepository, GenericMapper genericMapper) {
        this.driverRepository = driverRepository;
        this.carDriverRepository = carDriverRepository;
        this.genericMapper = genericMapper;
    }

    protected Driver createDriver(BudgetDtoIn dto, Car car) {
        Driver driver = genericMapper.toObject(dto, Driver.class);
        driverRepository.save(driver);
        CarDriver carDriver = new CarDriver(car, driver, dto.isMainDriver());
        carDriverRepository.save(carDriver);
        return driver;
    }
}

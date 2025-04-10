package br.com.audsat.vehicleinsurancews.service;

import br.com.audsat.vehicleinsurancews.config.mapper.GenericMapper;
import br.com.audsat.vehicleinsurancews.dto.BudgetDtoIn;
import br.com.audsat.vehicleinsurancews.model.Car;
import br.com.audsat.vehicleinsurancews.model.Driver;
import br.com.audsat.vehicleinsurancews.repository.CarRepository;
import org.springframework.stereotype.Service;

@Service
public class CarService {
    private final CarRepository carRepository;
    private final GenericMapper genericMapper;

    public CarService(CarRepository carRepository, GenericMapper genericMapper) {
        this.carRepository = carRepository;
        this.genericMapper = genericMapper;
    }

    protected Car createCar(BudgetDtoIn dto) {
        Car car = genericMapper.toObject(dto, Car.class);
        carRepository.save(car);
        return car;
    }
}

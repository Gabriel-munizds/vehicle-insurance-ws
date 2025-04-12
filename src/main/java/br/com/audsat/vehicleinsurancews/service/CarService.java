package br.com.audsat.vehicleinsurancews.service;

import br.com.audsat.vehicleinsurancews.config.mapper.GenericMapper;
import br.com.audsat.vehicleinsurancews.dto.BudgetDtoIn;
import br.com.audsat.vehicleinsurancews.model.Car;
import br.com.audsat.vehicleinsurancews.model.Driver;
import br.com.audsat.vehicleinsurancews.model.Model;
import br.com.audsat.vehicleinsurancews.repository.CarRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CarService {
    private final CarRepository carRepository;
    private final ModelService modelService;
    private final GenericMapper genericMapper;

    public CarService(CarRepository carRepository, ModelService modelService, GenericMapper genericMapper) {
        this.carRepository = carRepository;
        this.modelService = modelService;
        this.genericMapper = genericMapper;
    }

    private Car createCar(BudgetDtoIn dto) {
        Model model = modelService.returnModel(dto);
        Car car = genericMapper.toObject(dto, Car.class);
        car.setModel(model);
        carRepository.save(car);
        return car;
    }

    protected Car returnCar(BudgetDtoIn dto) {
        Optional<Car> car = carRepository.findByLicensePlate(dto.getLicensePlate());
        return car.orElseGet(() -> createCar(dto));
    }
}

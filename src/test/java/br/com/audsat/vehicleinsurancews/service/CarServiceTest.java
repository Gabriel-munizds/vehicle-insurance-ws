package br.com.audsat.vehicleinsurancews.service;

import br.com.audsat.vehicleinsurancews.config.mapper.GenericMapper;
import br.com.audsat.vehicleinsurancews.dto.BudgetDtoIn;
import br.com.audsat.vehicleinsurancews.model.Car;
import br.com.audsat.vehicleinsurancews.model.Model;
import br.com.audsat.vehicleinsurancews.repository.CarRepository;
import br.com.audsat.vehicleinsurancews.useful.PojoFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CarServiceTest {

    @Mock
    private CarRepository carRepository;

    @Mock
    private ModelService modelService;

    @Mock
    private GenericMapper genericMapper;

    @InjectMocks
    private CarService carService;

    @Test
    @DisplayName("should Return Existing Car When Found")
    void returnCar() {
        BudgetDtoIn dto = new BudgetDtoIn();
        dto.setLicensePlate("ABC1234");

        Car existingCar = new Car();
        existingCar.setLicensePlate("ABC1234");

        when(carRepository.findByLicensePlate("ABC1234")).thenReturn(Optional.of(existingCar));

        Car result = carService.returnCar(dto);

        assertSame(existingCar, result);
        verify(carRepository).findByLicensePlate("ABC1234");
        verifyNoMoreInteractions(carRepository, modelService, genericMapper);
    }

    @Test
    @DisplayName("should Create New Car When Not Found")
    void returnCar_Case2() {
        BudgetDtoIn dto = new BudgetDtoIn();
        dto.setLicensePlate("ABC1D23");

        Car car = PojoFactory.createValidCar();

        when(carRepository.findByLicensePlate("ABC1D23")).thenReturn(Optional.empty());
        when(modelService.returnModel(dto)).thenReturn(car.getModel());
        when(genericMapper.toObject(dto, Car.class)).thenReturn(car);
        when(carRepository.save(any(Car.class))).thenReturn(car);

        Car result = carService.returnCar(dto);

        assertSame(car, result);
        verify(carRepository).findByLicensePlate("ABC1D23");
        verify(modelService).returnModel(dto);
        verify(genericMapper).toObject(dto, Car.class);
        verify(carRepository).save(car);
    }

    @Test
    @DisplayName("should Update Existing Car Properties")
    void updateCar() {
        BudgetDtoIn dto = new BudgetDtoIn();
        dto.setLicensePlate("NEW123");

        Model model = new Model();
        Car sourceCar = new Car();
        sourceCar.setLicensePlate("NEW123");
        sourceCar.setModel(model);

        Car targetCar = new Car();
        targetCar.setId(1L);
        targetCar.setLicensePlate("OLD123");

        when(modelService.returnModel(dto)).thenReturn(model);
        when(genericMapper.toObject(dto, Car.class)).thenReturn(sourceCar);
        when(carRepository.save(targetCar)).thenReturn(targetCar);

        Car result = carService.updateCar(dto, targetCar);

        assertSame(targetCar, result);
        assertEquals(1L, targetCar.getId());
        assertEquals("NEW123", targetCar.getLicensePlate());
        assertSame(model, targetCar.getModel());

        verify(modelService).returnModel(dto);
        verify(genericMapper).toObject(dto, Car.class);
        verify(carRepository).save(targetCar);
    }
}
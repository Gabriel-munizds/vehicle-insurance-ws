package br.com.audsat.vehicleinsurancews.config.mapper;

import br.com.audsat.vehicleinsurancews.dto.BudgetDtoIn;
import br.com.audsat.vehicleinsurancews.dto.BudgetDtoOut;
import br.com.audsat.vehicleinsurancews.dto.CustomerDtoIn;
import br.com.audsat.vehicleinsurancews.model.Car;
import br.com.audsat.vehicleinsurancews.model.Customer;
import br.com.audsat.vehicleinsurancews.model.Driver;
import br.com.audsat.vehicleinsurancews.model.Insurance;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper(){
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.createTypeMap(BudgetDtoIn.class, Driver.class)
                .addMapping(BudgetDtoIn::getDriverName, Driver::setName)
                .addMapping(BudgetDtoIn::getDriverDocument, Driver::setDocument)
                .addMapping(BudgetDtoIn::getBirthdateDriver, Driver::setBirthdate);


        modelMapper.createTypeMap(CustomerDtoIn.class, Customer.class)
                .addMapping(CustomerDtoIn::getCustomerName, Customer::setName);

        modelMapper.createTypeMap(BudgetDtoIn.class, Car.class)
                .addMapping(BudgetDtoIn::getModelCar, Car::setModel)
                .addMapping(BudgetDtoIn::getManufacturerCar, Car::setManufacturer)
                .addMapping(BudgetDtoIn::getModelYearCar, Car::setModelYear)
                .addMapping(BudgetDtoIn::getFipeValueCar, Car::setFipeValue);

        modelMapper.createTypeMap(Insurance.class, BudgetDtoOut.class)
                .addMapping(source -> source.getCar().getModel(), BudgetDtoOut::setModelCar)
                .addMapping(source -> source.getCar().getModelYear(), BudgetDtoOut::setModelYearCar)
                .addMapping(source -> source.getCar().getFipeValue(), BudgetDtoOut::setFipeValueCar)
                .addMapping(source -> source.getCar().getManufacturer(), BudgetDtoOut::setManufacturerCar)
                .addMapping(source -> source.getCustomer().getDriver().getDocument(), BudgetDtoOut::setDocumentDriver)
                .addMapping(source -> source.getCustomer().getDriver().getName(), BudgetDtoOut::setNameDriver)
                .addMapping(source -> source.getCustomer().getDriver().getBirthdate(), BudgetDtoOut::setBirthdateDriver)
                .addMapping(Insurance::getCreationDate, BudgetDtoOut::setCreationDate)
                .addMapping(Insurance::getAliquot, BudgetDtoOut::setAliquot)
                .addMapping(Insurance::getUpdatedAt, BudgetDtoOut::setUpdatedAt);

        return modelMapper;
    }
}

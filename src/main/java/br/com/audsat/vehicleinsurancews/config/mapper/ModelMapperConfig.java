package br.com.audsat.vehicleinsurancews.config.mapper;

import br.com.audsat.vehicleinsurancews.dto.BudgetDtoIn;
import br.com.audsat.vehicleinsurancews.dto.BudgetDtoOut;
import br.com.audsat.vehicleinsurancews.model.*;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.createTypeMap(BudgetDtoIn.class, Driver.class)
                .addMapping(BudgetDtoIn::getDriverDocument, Driver::setDocument)
                .addMapping(BudgetDtoIn::getBirthdateDriver, Driver::setBirthdate);


        modelMapper.createTypeMap(BudgetDtoIn.class, Customer.class)
                .addMapping(BudgetDtoIn::getCustomerName, Customer::setName);

        modelMapper.createTypeMap(BudgetDtoIn.class, Car.class)
                .addMapping(BudgetDtoIn::getLicensePlate, Car::setLicensePlate);

        modelMapper.createTypeMap(Insurance.class, BudgetDtoOut.class)
                .addMapping(source -> source.getCar().getModel().getDescription(), BudgetDtoOut::setModelCar)
                .addMapping(source -> source.getCar().getModel().getModelYear(), BudgetDtoOut::setModelYearCar)
                .addMapping(source -> source.getCar().getModel().getFipeValue(), BudgetDtoOut::setFipeValueCar)
                .addMapping(source -> source.getCar().getModel().getManufacturer(), BudgetDtoOut::setManufacturerCar)
                .addMapping(source -> source.getCustomer().getDriver().getDocument(), BudgetDtoOut::setDocumentDriver)
                .addMapping(source -> source.getCustomer().getName(), BudgetDtoOut::setCustomerName)
                .addMapping(source -> source.getCustomer().getDriver().getBirthdate(), BudgetDtoOut::setBirthdateDriver)
                .addMapping(Insurance::getCreationDate, BudgetDtoOut::setCreationDate)
                .addMapping(Insurance::getAliquot, BudgetDtoOut::setAliquot)
                .addMapping(Insurance::getUpdatedAt, BudgetDtoOut::setUpdatedAt);


        modelMapper.createTypeMap(BudgetDtoIn.class, Model.class)
                .addMapping(BudgetDtoIn::getModelCar, Model::setDescription)
                .addMapping(BudgetDtoIn::getFipeCodeModelCar, Model::setFipeCode)
                .addMapping(BudgetDtoIn::getFipeValueModelCar, Model::setFipeValue)
                .addMapping(BudgetDtoIn::getFuelType, Model::setFuelType)
                .addMapping(BudgetDtoIn::getModelYearCar, Model::setModelYear)
                .addMapping(BudgetDtoIn::getManufacturerModelCar, Model::setManufacturer);

        return modelMapper;
    }
}

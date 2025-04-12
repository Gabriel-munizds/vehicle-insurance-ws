package br.com.audsat.vehicleinsurancews.service;

import br.com.audsat.vehicleinsurancews.config.mapper.GenericMapper;
import br.com.audsat.vehicleinsurancews.dto.BudgetDtoIn;
import br.com.audsat.vehicleinsurancews.model.Model;
import br.com.audsat.vehicleinsurancews.repository.ModelRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ModelService {
    private final ModelRepository modelRepository;
    private final GenericMapper genericMapper;

    public ModelService(ModelRepository modelRepository, GenericMapper genericMapper) {
        this.modelRepository = modelRepository;
        this.genericMapper = genericMapper;
    }

    protected Model returnModel(BudgetDtoIn dto) {
        Optional<Model> model = modelRepository.findByFipeCode(dto.getFipeCodeModelCar());
        return model.orElseGet(() -> createModel(dto));
    }

    private Model createModel(BudgetDtoIn dto) {
        Model model = genericMapper.toObject(dto, Model.class);
        model.setLastUpdateFipeValue(LocalDateTime.now());
        modelRepository.save(model);
        return model;
    }
}

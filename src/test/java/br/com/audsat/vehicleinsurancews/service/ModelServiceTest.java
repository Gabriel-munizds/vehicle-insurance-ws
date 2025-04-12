package br.com.audsat.vehicleinsurancews.service;

import br.com.audsat.vehicleinsurancews.config.mapper.GenericMapper;
import br.com.audsat.vehicleinsurancews.dto.BudgetDtoIn;
import br.com.audsat.vehicleinsurancews.model.Model;
import br.com.audsat.vehicleinsurancews.repository.ModelRepository;
import br.com.audsat.vehicleinsurancews.useful.PojoFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ModelServiceTest {

    @Mock
    private ModelRepository modelRepository;

    @Mock
    private GenericMapper genericMapper;

    @InjectMocks
    private ModelService modelService;

    @Test
    void returnModel_shouldReturnExistingModel_whenFoundByFipeCode() {
        BudgetDtoIn dto = PojoFactory.createValidBudgetDtoIn();
        Model existingModel = PojoFactory.createValidModel();

        when(modelRepository.findByFipeCode("12345678"))
                .thenReturn(Optional.of(existingModel));

        Model result = modelService.returnModel(dto);

        assertSame(existingModel, result);
        verify(modelRepository).findByFipeCode("12345678");
        verifyNoInteractions(genericMapper);
        verifyNoMoreInteractions(modelRepository);
    }

    @Test
    void returnModel_shouldCreateNewModel_whenNotFound() {
        BudgetDtoIn dto = PojoFactory.createValidBudgetDtoIn();

        Model newModel = PojoFactory.createValidModel();

        when(modelRepository.findByFipeCode("12345678"))
                .thenReturn(Optional.empty());
        when(genericMapper.toObject(dto, Model.class))
                .thenReturn(newModel);
        when(modelRepository.save(any(Model.class)))
                .thenReturn(newModel);

        Model result = modelService.returnModel(dto);

        assertSame(newModel, result);
        assertNotNull(newModel.getLastUpdateFipeValue());
        verify(modelRepository).findByFipeCode("12345678");
        verify(genericMapper).toObject(dto, Model.class);
        verify(modelRepository).save(newModel);
    }

}
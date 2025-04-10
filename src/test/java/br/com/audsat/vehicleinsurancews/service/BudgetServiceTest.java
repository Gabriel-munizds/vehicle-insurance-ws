package br.com.audsat.vehicleinsurancews.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class BudgetServiceTest {
    @InjectMocks
    private BudgetService budgetService;

    @Test
    void createBudget_shouldReturnBudgetDtoOutWhenValidInput() {
    }

    @Test
    void createBudget_shouldThrowExceptionWhenInvalidInput() {
    }

    @Test
    void findBudgetByInsuranceId_shouldReturnBudgetDtoOutWhenInsuranceIdExists() {
    }

    @Test
    void findBudgetByInsuranceId_shouldThrowNotFoundExceptionWhenInsuranceIdDoesNotExist() {
    }

    @Test
    void updateBudget_shouldReturnUpdatedBudgetDtoOutWhenValidInput() {
    }

    @Test
    void updateBudget_shouldThrowNotFoundExceptionWhenInsuranceIdDoesNotExist() {
    }

    @Test
    void deleteBudgetByInsuranceId_shouldDeleteBudgetWhenInsuranceIdExists() {
    }

    @Test
    void deleteBudgetByInsuranceId_shouldThrowNotFoundExceptionWhenInsuranceIdDoesNotExist() {
    }
}

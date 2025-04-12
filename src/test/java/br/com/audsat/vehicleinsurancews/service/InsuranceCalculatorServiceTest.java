package br.com.audsat.vehicleinsurancews.service;

import br.com.audsat.vehicleinsurancews.model.valueobjects.InsuranceValue;
import br.com.audsat.vehicleinsurancews.model.valueobjects.RiskProfile;
import br.com.audsat.vehicleinsurancews.utils.PojoFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class InsuranceCalculatorServiceTest {
    private RiskProfile riskProfile;
    @InjectMocks
    private InsuranceCalculatorService insuranceCalculatorService;

    @Test
    @DisplayName("should return insurance value when no risk factors")
    void calculateInsuranceValue_Case1() {
        BigDecimal fipeValue = new BigDecimal("100000.00");
        riskProfile = PojoFactory.createValidRiskProfile();
        InsuranceValue result = insuranceCalculatorService.calculateInsuranceValue(fipeValue, riskProfile);
        assertEquals(new BigDecimal("6000.00"), result.getValue());
        assertEquals(new BigDecimal("0.06"), result.getAliquot());
    }

    @Test
    @DisplayName("should return insurance value when young driver risk applies ")
    void calculateInsuranceValue_Case2() {
        BigDecimal fipeValue = new BigDecimal("100000.00");
        riskProfile = PojoFactory.createRiskProfileWithYoungDriverRisk();
        InsuranceValue result = insuranceCalculatorService.calculateInsuranceValue(fipeValue, riskProfile);
        assertEquals(new BigDecimal("8000.00"), result.getValue());
        assertEquals(new BigDecimal("0.08"), result.getAliquot());
    }

    @Test
    @DisplayName("should return insurance value when young driver and driver claims risk applies ")
    void calculateInsuranceValue_Case3() {
        BigDecimal fipeValue = new BigDecimal("100000.00");
        riskProfile = PojoFactory.createRiskProfileWithYoungDriverRiskAndDriverClaimsRisk();
        InsuranceValue result = insuranceCalculatorService.calculateInsuranceValue(fipeValue, riskProfile);
        assertEquals(new BigDecimal("10000.00"), result.getValue());
        assertEquals(new BigDecimal("0.10"), result.getAliquot());
    }

    @Test
    @DisplayName("should return insurance value when young driver, driver claims and vehicle claims risk applies ")
    void calculateInsuranceValue_Case4() {
        BigDecimal fipeValue = new BigDecimal("100000.00");
        riskProfile = PojoFactory.createRiskProfileWithYoungDriverRiskAndDriverClaimsRiskAndVehicleClaimsRisk();
        InsuranceValue result = insuranceCalculatorService.calculateInsuranceValue(fipeValue, riskProfile);
        assertEquals(new BigDecimal("12000.00"), result.getValue());
        assertEquals(new BigDecimal("0.12"), result.getAliquot());
    }
}
package br.com.audsat.vehicleinsurancews.service.strategy.impl;

import br.com.audsat.vehicleinsurancews.model.valueobjects.RiskProfile;
import br.com.audsat.vehicleinsurancews.utils.PojoFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class YoungDriverStrategyTest {

    private final YoungDriverStrategy strategy = new YoungDriverStrategy();

    @Test
    void shouldApply_shouldReturnTrueForYoungMainDriver() {
        RiskProfile riskProfile = PojoFactory.createRiskProfileWithYoungDriverRisk();
        assertTrue(strategy.shouldApply(riskProfile));
    }

    @Test
    void shouldApply_shouldReturnFalseForNonMainDriver() {
        RiskProfile riskProfile = PojoFactory.createRiskProfileWithYoungDriverRiskButIsNotMainDriver();
        assertFalse(strategy.shouldApply(riskProfile));
    }

    @Test
    void shouldApply_shouldReturnFalseForDriversOver25() {
        RiskProfile riskProfile = PojoFactory.createValidRiskProfile();
        assertFalse(strategy.shouldApply(riskProfile));
    }

    @Test
    void getAdditionalPercentage_shouldReturn2Percent() {
        assertEquals(new BigDecimal("0.02"), strategy.getAdditionalPercentage());
    }
}
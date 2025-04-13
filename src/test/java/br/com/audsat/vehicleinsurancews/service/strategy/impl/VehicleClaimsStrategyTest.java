package br.com.audsat.vehicleinsurancews.service.strategy.impl;

import br.com.audsat.vehicleinsurancews.model.valueobjects.RiskProfile;
import br.com.audsat.vehicleinsurancews.useful.PojoFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

@ExtendWith(MockitoExtension.class)
class VehicleClaimsStrategyTest {

    private final VehicleClaimsStrategy strategy = new VehicleClaimsStrategy();

    @Test
    void shouldApply_shouldReturnTrueWhenVehicleHasClaims() {
        RiskProfile riskProfile = PojoFactory.createRiskProfileWithYoungDriverRiskAndDriverClaimsRiskAndVehicleClaimsRisk();
        assertTrue(strategy.shouldApply(riskProfile));
    }

    @Test
    void shouldApply_shouldReturnFalseWhenVehicleHasNoClaims() {
        RiskProfile riskProfile = PojoFactory.createValidRiskProfile();
        assertFalse(strategy.shouldApply(riskProfile));
    }

    @Test
    void getAdditionalPercentage_shouldReturn2Percent() {
        assertEquals(new BigDecimal("0.02"), strategy.getAdditionalPercentage());
    }
}
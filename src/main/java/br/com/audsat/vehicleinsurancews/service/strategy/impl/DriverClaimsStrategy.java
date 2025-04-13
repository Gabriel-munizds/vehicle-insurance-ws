package br.com.audsat.vehicleinsurancews.service.strategy.impl;

import br.com.audsat.vehicleinsurancews.model.valueobjects.RiskProfile;
import br.com.audsat.vehicleinsurancews.service.strategy.RiskCalculationStrategy;

import java.math.BigDecimal;

public class DriverClaimsStrategy implements RiskCalculationStrategy {
    @Override
    public Boolean shouldApply(RiskProfile riskProfile) {
        return riskProfile.getHasDriverClaims();
    }

    @Override
    public BigDecimal getAdditionalPercentage() {
        return new BigDecimal("0.02");
    }
}

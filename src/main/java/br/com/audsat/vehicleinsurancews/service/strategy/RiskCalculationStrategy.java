package br.com.audsat.vehicleinsurancews.service.strategy;

import br.com.audsat.vehicleinsurancews.model.valueobjects.RiskProfile;

import java.math.BigDecimal;

public interface RiskCalculationStrategy {
    Boolean shouldApply(RiskProfile riskProfile);
    BigDecimal getAdditionalPercentage();
}

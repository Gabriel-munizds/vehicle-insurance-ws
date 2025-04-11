package br.com.audsat.vehicleinsurancews.service.strategy.impl;

import br.com.audsat.vehicleinsurancews.model.valueobjects.RiskProfile;
import br.com.audsat.vehicleinsurancews.service.strategy.RiskCalculationStrategy;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;

public class YoungDriverStrategy implements RiskCalculationStrategy {
    @Override
    public Boolean shouldApply(RiskProfile riskProfile) {
        int ageDriver = Period.between(riskProfile.getBirthdateDriver(), LocalDate.now()).getYears();
        if (riskProfile.getMainDriver() && ageDriver > 18 && ageDriver < 25) return Boolean.TRUE;
        return Boolean.FALSE;
    }

    @Override
    public BigDecimal getAdditionalPercentage() {
        return new BigDecimal("0.02");
    }
}

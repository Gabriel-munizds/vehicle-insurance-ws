package br.com.audsat.vehicleinsurancews.service;

import br.com.audsat.vehicleinsurancews.model.valueobjects.InsuranceValue;
import br.com.audsat.vehicleinsurancews.model.valueobjects.RiskProfile;
import br.com.audsat.vehicleinsurancews.service.strategy.RiskCalculationStrategy;
import br.com.audsat.vehicleinsurancews.service.strategy.impl.DriverClaimsStrategy;
import br.com.audsat.vehicleinsurancews.service.strategy.impl.VehicleClaimsStrategy;
import br.com.audsat.vehicleinsurancews.service.strategy.impl.YoungDriverStrategy;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class InsuranceCalculatorService {

    private static final BigDecimal BASE_ALIQUOT = new BigDecimal("0.06");
    private final List<RiskCalculationStrategy> strategies;

    public InsuranceCalculatorService() {
        this.strategies = List.of(
                new YoungDriverStrategy(),
                new DriverClaimsStrategy(),
                new VehicleClaimsStrategy()
        );
    }

    protected InsuranceValue calculateInsuranceValue(BigDecimal fipeValue, RiskProfile riskProfile) {
        BigDecimal totalAliquot = BASE_ALIQUOT;
        for (RiskCalculationStrategy strategy : strategies) {
            if (strategy.shouldApply(riskProfile)) {
                totalAliquot = totalAliquot.add(strategy.getAdditionalPercentage());
            }
        }
        return new InsuranceValue(fipeValue, totalAliquot);
    }

}

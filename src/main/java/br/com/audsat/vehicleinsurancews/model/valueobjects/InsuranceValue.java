package br.com.audsat.vehicleinsurancews.model.valueobjects;

import br.com.audsat.vehicleinsurancews.service.strategy.RiskCalculationStrategy;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public final class InsuranceValue {
    private final BigDecimal value;
    private final BigDecimal aliquot;

    public InsuranceValue(BigDecimal baseValue, BigDecimal totalAliquot) {
        Objects.requireNonNull(baseValue, "Base value cannot be null");
        Objects.requireNonNull(totalAliquot, "Aliquot cannot be null");

        if (baseValue.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Base value cannot be negative");
        }

        if (totalAliquot.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Percentage cannot be negative");
        }
        this.value = baseValue.multiply(totalAliquot).setScale(2, RoundingMode.HALF_EVEN);
        this.aliquot = totalAliquot;
    }

    public BigDecimal getValue() {
        return value;
    }

    public BigDecimal getAliquot() {
        return aliquot;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InsuranceValue that = (InsuranceValue) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}

package br.com.audsat.vehicleinsurancews.model.valueobjects;

import java.time.LocalDate;
import java.util.Objects;

public final class RiskProfile {
    private final LocalDate birthdateDriver;
    private final Boolean isMainDriver;
    private final Boolean hasDriverClaims;
    private final Boolean hasVehicleClaims;

    public RiskProfile(LocalDate birthdateDriver, Boolean isMainDriver, Boolean hasDriverClaims, Boolean hasVehicleClaims) {
        this.birthdateDriver = birthdateDriver;
        this.isMainDriver = isMainDriver;
        this.hasDriverClaims = hasDriverClaims;
        this.hasVehicleClaims = hasVehicleClaims;
    }

    public static RiskProfile factory(LocalDate birthdateDriver, Boolean isMainDriver, Boolean hasDriverClaims, Boolean hasVehicleClaims) {
        return new RiskProfile(birthdateDriver, isMainDriver, hasDriverClaims, hasVehicleClaims);
    }

    public LocalDate getBirthdateDriver() {
        return birthdateDriver;
    }

    public Boolean getMainDriver() {
        return isMainDriver;
    }

    public Boolean getHasDriverClaims() {
        return hasDriverClaims;
    }

    public Boolean getHasVehicleClaims() {
        return hasVehicleClaims;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RiskProfile that = (RiskProfile) o;
        return Objects.equals(birthdateDriver, that.birthdateDriver) && Objects.equals(isMainDriver, that.isMainDriver) && Objects.equals(hasDriverClaims, that.hasDriverClaims) && Objects.equals(hasVehicleClaims, that.hasVehicleClaims);
    }

    @Override
    public int hashCode() {
        return Objects.hash(birthdateDriver, isMainDriver, hasDriverClaims, hasVehicleClaims);
    }
}

package br.com.audsat.vehicleinsurancews.model.valueobjects;

import java.time.LocalDate;

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
}

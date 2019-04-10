package io.github.hobbstech.sarah_core_ambient_conditions_management.service;

import lombok.Getter;

public enum TypeOfGas {

    LPG(150.0, 200.0),
    SMOKE(200.0, 300.0),
    FLAMABLE(300.0, 1024.0);

    @Getter
    private Double minRangeValue;

    @Getter
    private Double maxRangeValue;

    TypeOfGas(Double minRangeValue, Double maxRangeValue) {
        this.minRangeValue = minRangeValue;
        this.maxRangeValue = maxRangeValue;
    }
}

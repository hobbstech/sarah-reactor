package io.github.hobbstech.sarah_core_ambient_conditions_management.service.gas;

import java.util.stream.Stream;

public class GasDeterminer {

    private GasDeterminer() {
    }

    public static TypeOfGas resolveGas(Double gasValue) {
        return Stream.of(TypeOfGas.values())
                .filter(typeOfGas -> (typeOfGas.getMinRangeValue() <= gasValue) && (gasValue <= typeOfGas.getMaxRangeValue()))
                .findFirst().get();
    }
}

package io.github.hobbstech.sarah_core_ambient_conditions_management.domain;

import io.github.hobbstech.sarah_core_ambient_conditions_management.service.TypeOfGas;
import io.github.hobbstech.sarah_core_utils.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class GasRecord extends BaseEntity {

    private Double gasValue;

    @Enumerated
    private TypeOfGas typeOfGas;

    @ManyToOne
    private Room room;

}

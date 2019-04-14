package io.github.hobbstech.sarah_core_ambient_conditions_management.domain;

import io.github.hobbstech.sarah_core_utils.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class MoistureRecord extends BaseEntity {

    private Double moistureValue;

    @ManyToOne
    private Room room;

}

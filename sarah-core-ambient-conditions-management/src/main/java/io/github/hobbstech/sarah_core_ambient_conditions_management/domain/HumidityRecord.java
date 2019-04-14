package io.github.hobbstech.sarah_core_ambient_conditions_management.domain;

import io.github.hobbstech.sarah_core_utils.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class HumidityRecord extends BaseEntity {

    private Double humidity;

    @ManyToOne
    private Room room;

}

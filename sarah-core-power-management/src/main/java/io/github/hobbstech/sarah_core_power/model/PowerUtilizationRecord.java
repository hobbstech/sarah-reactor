package io.github.hobbstech.sarah_core_power.model;

import io.github.hobbstech.sarah_core_ambient_conditions_management.domain.Room;
import io.github.hobbstech.sarah_core_utils.domain.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@EqualsAndHashCode(callSuper = true)
public class PowerUtilizationRecord extends BaseEntity {

    private Double dayAvgPowerUsage;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date recordedAt;

    @ManyToOne
    private Room room;

}

package io.github.hobbstech.sarah_core_ambient_conditions_management.domain;

import io.github.hobbstech.sarah_core_utils.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class TemperatureRecord extends BaseEntity {

    @Column
    private LocalDate date;

    @Column
    private Double temperature;

    @Column
    private Double maxTemp;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date maxTempTime;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date minTempTime;

    @Column
    private Double minTemp;

    @ManyToOne
    private Room room;

    @PrePersist
    public void updatePreconditions() {
        date = LocalDate.now();
    }


}

package io.github.hobbstech.sarah_core_ambient_conditions_management.domain;

import io.github.hobbstech.sarah_core_utils.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class TemperatureRecord extends BaseEntity {

    @Temporal(TemporalType.DATE)
    @Column
    @CreationTimestamp
    private Date date;

    @Column
    private Double temperature;

    @Column
    private Double maxTemp;

    @Column
    private Double minTemp;

}

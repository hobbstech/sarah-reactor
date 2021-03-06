package io.github.hobbstech.sarah_core_utils.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
@Getter
@Setter
public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @CreationTimestamp
    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;

    @UpdateTimestamp
    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date datelastUpdated;

    @Version
    private Integer version;

}

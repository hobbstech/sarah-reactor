package tech.hobbs.sarah_core_entertainment.music.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import tech.hobbs.sarah_core_utils.domain.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class MusicRecord extends BaseEntity {

    @Column
    private String recordName;

    @Column
    private String fileType;

    @Enumerated
    private Genre genre;


}

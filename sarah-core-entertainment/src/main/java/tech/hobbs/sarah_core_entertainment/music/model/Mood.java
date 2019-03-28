package tech.hobbs.sarah_core_entertainment.music.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import tech.hobbs.sarah_core_utils.domain.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Enumerated;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
public class Mood extends BaseEntity {

    @Enumerated
    private MoodType name;

    @Enumerated
    private Genre genre;

}

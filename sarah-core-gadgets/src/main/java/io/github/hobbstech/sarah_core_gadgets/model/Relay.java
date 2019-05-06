package io.github.hobbstech.sarah_core_gadgets.model;

import io.github.hobbstech.sarah_core_utils.domain.BaseEntity;
import lombok.*;

import javax.persistence.Entity;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = true)
public class Relay extends BaseEntity {

    private Long relayNumber;

}

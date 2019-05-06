package io.github.hobbstech.sarah_core_gadgets.model;

import io.github.hobbstech.sarah_core_utils.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToOne;

import static io.github.hobbstech.sarah_core_gadgets.model.GadgetStatus.OFF;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@Builder
public class Gadget extends BaseEntity {

    @OneToOne
    private Relay relay;

    private String gadgetName;

    @Enumerated(value = EnumType.STRING)
    private GadgetStatus gadgetStatus;

    public Gadget() {
        gadgetStatus = OFF;
    }
}

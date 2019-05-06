package io.github.hobbstech.sarah_core_gadgets.dtos;

import io.github.hobbstech.sarah_core_gadgets.model.Gadget;
import lombok.Data;

@Data
public class GadgetDto {

    private Long relayId;

    private String gadgetName;


    public Gadget toGadget(GadgetDto gadgetDto) {

        if (gadgetDto == null) {
            return null;
        }
        Gadget gadget = new Gadget();
        gadget.setGadgetName(gadgetDto.getGadgetName());

        return gadget;

    }
}

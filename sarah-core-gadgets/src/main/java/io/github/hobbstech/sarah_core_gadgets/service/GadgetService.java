package io.github.hobbstech.sarah_core_gadgets.service;

import io.github.hobbstech.sarah_core_gadgets.dtos.GadgetDto;
import io.github.hobbstech.sarah_core_gadgets.model.Gadget;

import java.util.Collection;

public interface GadgetService {

    Gadget addGadget(GadgetDto gadgetDto);

    Gadget updateGadget(Long gadgetId, GadgetDto gadgetDto);

    void deleteGadget(Long gadgetId);

    Gadget findById(Long id);

    Collection<Gadget> findAll();

}

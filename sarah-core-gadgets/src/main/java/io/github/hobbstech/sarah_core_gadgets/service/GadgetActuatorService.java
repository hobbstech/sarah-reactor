package io.github.hobbstech.sarah_core_gadgets.service;

import io.github.hobbstech.sarah_core_gadgets.model.Gadget;
import io.github.hobbstech.sarah_core_gadgets.model.GadgetStatus;

public interface GadgetActuatorService {

    Gadget actuateGadget(Long gadgetId, GadgetStatus gadgetStatus);

}

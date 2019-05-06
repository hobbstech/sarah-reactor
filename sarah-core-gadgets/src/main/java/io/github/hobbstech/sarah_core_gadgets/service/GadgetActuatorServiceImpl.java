package io.github.hobbstech.sarah_core_gadgets.service;

import io.github.hobbstech.sarah_core_gadgets.model.Gadget;
import io.github.hobbstech.sarah_core_gadgets.model.GadgetStatus;
import io.github.hobbstech.sarah_core_gadgets.repository.GadgetRepository;

public class GadgetActuatorServiceImpl implements GadgetActuatorService {

    private final GadgetRepository gadgetRepository;

    private final RelayServer relayServer;

    public GadgetActuatorServiceImpl(GadgetRepository gadgetRepository, RelayServer relayServer) {
        this.gadgetRepository = gadgetRepository;
        this.relayServer = relayServer;
    }

    @Override
    public Gadget actuateGadget(Long gadgetId, GadgetStatus gadgetStatus) {
        var gadget = gadgetRepository.findById(gadgetId).orElseThrow();
        gadget.setGadgetStatus(gadgetStatus);
        gadget = gadgetRepository.save(gadget);

        relayServer.turnOnRelay(gadget.getRelay().getId(), gadgetStatus);

        return gadget;
    }
}

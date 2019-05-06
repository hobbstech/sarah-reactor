package io.github.hobbstech.sarah_core_gadgets.service;

import io.github.hobbstech.sarah_core_gadgets.dtos.GadgetDto;
import io.github.hobbstech.sarah_core_gadgets.model.Gadget;
import io.github.hobbstech.sarah_core_gadgets.repository.GadgetRepository;
import lombok.val;

import java.util.Collection;

public class GadgetServiceImpl implements GadgetService {

    private final GadgetRepository gadgetRepository;

    private final RelayService relayService;

    public GadgetServiceImpl(GadgetRepository gadgetRepository, RelayService relayService) {
        this.gadgetRepository = gadgetRepository;
        this.relayService = relayService;
    }

    @Override
    public Gadget addGadget(GadgetDto gadgetDto) {
        val relayId = gadgetDto.getRelayId();
        val relay = relayService.findById(relayId);
        val gadget = gadgetDto.toGadget(gadgetDto);
        gadget.setRelay(relay);
        return gadgetRepository.save(gadget);
    }

    @Override
    public Gadget updateGadget(Long gadgetId, GadgetDto gadgetDto) {
        val gadget = findById(gadgetId);
        val relayId = gadgetDto.getRelayId();
        val relay = relayService.findById(relayId);
        gadget.setRelay(relay);
        gadget.setGadgetName(gadgetDto.getGadgetName());
        return gadgetRepository.save(gadget);
    }

    @Override
    public void deleteGadget(Long gadgetId) {
        gadgetRepository.deleteById(gadgetId);
    }

    @Override
    public Gadget findById(Long id) {
        return gadgetRepository.findById(id)
                .orElseThrow();
    }

    @Override
    public Collection<Gadget> findAll() {
        return gadgetRepository.findAll();
    }
}

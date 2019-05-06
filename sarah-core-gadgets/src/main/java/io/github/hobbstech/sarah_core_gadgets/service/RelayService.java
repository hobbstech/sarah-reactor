package io.github.hobbstech.sarah_core_gadgets.service;

import io.github.hobbstech.sarah_core_gadgets.dtos.RelayDto;
import io.github.hobbstech.sarah_core_gadgets.model.Relay;

import java.util.Collection;

public interface RelayService {

    Relay addRelay(RelayDto relayDto);

    Relay updateRelay(Long relayId, RelayDto relayDto);

    void deleteRelay(Long relayId);

    Relay findById(Long id);

    Collection<Relay> findAll();

}

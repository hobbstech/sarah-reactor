package io.github.hobbstech.sarah_core_gadgets.service;

import io.github.hobbstech.sarah_core_gadgets.dtos.RelayDto;
import io.github.hobbstech.sarah_core_gadgets.model.Relay;
import io.github.hobbstech.sarah_core_gadgets.repository.RelayRepository;
import lombok.val;

import java.util.Collection;

public class RelayServiceImpl implements RelayService {

    private final RelayRepository relayRepository;

    public RelayServiceImpl(RelayRepository relayRepository) {
        this.relayRepository = relayRepository;
    }

    @Override
    public Relay addRelay(RelayDto relayDto) {
        val relay = RelayDto.toRelay(relayDto);
        return relayRepository.save(relay);
    }

    @Override
    public Relay updateRelay(Long relayId, RelayDto relayDto) {
        val relay = findById(relayId);
        relay.setRelayNumber(relayDto.getRelayNumber());
        return relayRepository.save(relay);
    }

    @Override
    public void deleteRelay(Long relayId) {
        relayRepository.deleteById(relayId);
    }

    @Override
    public Relay findById(Long id) {
        return relayRepository.findById(id)
                .orElseThrow();
    }

    @Override
    public Collection<Relay> findAll() {
        return relayRepository.findAll();
    }
}

package io.github.hobbstech.sarah_core_gadgets.dtos;

import io.github.hobbstech.sarah_core_gadgets.model.Relay;
import lombok.Data;

@Data
public class RelayDto {

    private Long relayNumber;

    public static Relay toRelay(RelayDto relayDto) {

        if (relayDto == null) {
            return null;
        }
        Relay relay = new Relay();
        relay.setRelayNumber(relayDto.getRelayNumber());

        return relay;
    }

}

package io.github.hobbstech.sara_api.gadgets;

import io.github.hobbstech.sarah_core_gadgets.dtos.RelayDto;
import io.github.hobbstech.sarah_core_gadgets.model.Relay;
import io.github.hobbstech.sarah_core_gadgets.service.RelayService;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
public class RelayRestController {

    private final RelayService relayService;

    public RelayRestController(RelayService relayService) {
        this.relayService = relayService;
    }

    @PostMapping("/v1/relays")
    public Relay addRelay(@RequestBody RelayDto relayDto) {
        return relayService.addRelay(relayDto);
    }

    @PutMapping("/v1/relays/{relayId}")
    public Relay addRelay(@RequestBody RelayDto relayDto,
                          @PathVariable("relayId") Long relayId) {
        return relayService.updateRelay(relayId, relayDto);
    }

    @GetMapping("/v1/relays")
    public Collection<Relay> getAllRelays() {
        return relayService.findAll();
    }

    @GetMapping("/v1/relays/{relayId}")
    public Relay getRelay(@PathVariable("relayId") Long relayId) {
        return relayService.findById(relayId);
    }

    @DeleteMapping("/v1/relays/{relayId}")
    public void deleteRelay(@PathVariable("relayId") Long relayId) {
        relayService.deleteRelay(relayId);
    }
}

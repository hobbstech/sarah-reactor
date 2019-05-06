package io.github.hobbstech.sarah_core_gadgets.service;

import io.github.hobbstech.sarah_core_gadgets.model.GadgetStatus;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "relay-server", url = "http://192.168.1.11:8505")
public interface RelayServer {

    @PostMapping("/v1/relays")
    void turnOnRelay(@RequestParam("relayId") Long relayId,
                     @RequestParam("status") GadgetStatus status);

}

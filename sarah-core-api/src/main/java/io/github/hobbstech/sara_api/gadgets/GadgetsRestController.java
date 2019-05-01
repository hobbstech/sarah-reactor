package io.github.hobbstech.sara_api.gadgets;

import io.github.hobbstech.sarah_core_gadgets.dtos.GadgetDto;
import io.github.hobbstech.sarah_core_gadgets.model.Gadget;
import io.github.hobbstech.sarah_core_gadgets.model.GadgetStatus;
import io.github.hobbstech.sarah_core_gadgets.service.GadgetActuatorService;
import io.github.hobbstech.sarah_core_gadgets.service.GadgetService;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
public class GadgetsRestController {

    private final GadgetService gadgetService;

    private final GadgetActuatorService gadgetActuatorService;

    public GadgetsRestController(GadgetService gadgetService, GadgetActuatorService gadgetActuatorService) {
        this.gadgetService = gadgetService;
        this.gadgetActuatorService = gadgetActuatorService;
    }

    @PostMapping("/v1/gadgets")
    public Gadget addGadget(@RequestBody GadgetDto gadgetDto) {
        return gadgetService.addGadget(gadgetDto);
    }

    @PutMapping("/v1/gadgets/{gadgetId}")
    public Gadget addGadget(@RequestBody GadgetDto gadgetDto,
                            @PathVariable("gadgetId") Long gadgetId) {
        return gadgetService.updateGadget(gadgetId, gadgetDto);
    }

    @GetMapping("/v1/gadgets")
    public Collection<Gadget> getAllGadgets() {
        return gadgetService.findAll();
    }

    @GetMapping("/v1/gadgets/{gadgetId}")
    public Gadget getGadget(@PathVariable("gadgetId") Long gadgetId) {
        return gadgetService.findById(gadgetId);
    }

    @DeleteMapping("/v1/gadgets/{gadgetId}")
    public void deleteGadget(@PathVariable("gadgetId") Long gadgetId) {
        gadgetService.deleteGadget(gadgetId);
    }

    @PostMapping("/v1/gadgets/actuate")
    public Gadget actuateGadget(@RequestParam("gadgetId") Long gadgetId,
                                @RequestParam("status") GadgetStatus gadgetStatus) {
        return gadgetActuatorService.actuateGadget(gadgetId, gadgetStatus);
    }

}

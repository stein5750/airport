package airport.gateserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import airport.gateserver.domain.GateAvailability;
import airport.gateserver.service.AvailabilityService;
import reactor.core.publisher.Mono;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/gates")
public class AvailabilityController {

    AvailabilityController() {
    }

    @Autowired
    private AvailabilityService service;

    /*-
     * curl
     * -X GET "http://localhost:8080/airport-gateserver/gates/getAvailableGate"
     */
    @GetMapping(value = "/getAvailableGate")
    Mono<GateAvailability> getAvailableGate() {
        return service.getAvailableGate();
    }

    /*-
     * curl
     * -X GET
     * "http://localhost:8080/airport-gateserver/gates/save/A/true"
     */
    // --TODO change to post
    @GetMapping(value = "/save/{gateName}/{available}")
    public Mono<Void> saveGateStatus( @PathVariable("gateName") String gateName,
            @PathVariable("available") boolean available) {
        return service.saveGateAvailability(gateName, available);
    }

    /*-
     * curl
     * -X GET
     * "http://localhost:8080/airport-gateserver/gates/remove/A"
     */
    // --TODO change to post
    @GetMapping(value = "/remove/{gateName}")
    public void deleteGate( @PathVariable("gateName") String gateName) {
        service.removeGateStatus(gateName);
    }

}

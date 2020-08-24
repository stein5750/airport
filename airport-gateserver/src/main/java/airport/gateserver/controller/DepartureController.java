package airport.gateserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import airport.gateserver.domain.JourneyAndGateName;
import airport.gateserver.service.DepartureService;
import reactor.core.publisher.Mono;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/departures")
public class DepartureController {

    DepartureController() {
    }

    @Autowired
    private DepartureService service;

    /*-
     * curl
     * -H "Content-Type: application/json"
     * -X POST --data '{
     *      "flight":"BA888",
     *      "location":"Arlanda",
     *      "time":"2020-07-13T17:45:00.000000",
     *      "gateName":"E"
     * }'
     * "http://localhost:8080/airport-gateserver/departures/save"
     */
    @PostMapping(value = "/save")
    public Mono<Void> save( @RequestBody JourneyAndGateName journeyAndGateName) {
        return service.save(journeyAndGateName);
    }

    /*-
     * curl
     * -X GET
     * "http://localhost:8080/airport-gateserver/departures/get/A"
     *
     * return
     * {
     *      "flight":"LX216",
     *      "location":"Skopje",
     *      "time":[2020,8,22,16,24,42,75000000],
     *      "gateName":"A"
     * }
     */
    @GetMapping(value = "/get/{gateName}")
    public Mono<JourneyAndGateName> get( @PathVariable("gateName") String gateName) {
        return service.get(gateName);
    }

}
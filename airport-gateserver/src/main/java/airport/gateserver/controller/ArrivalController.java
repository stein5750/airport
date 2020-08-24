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
import airport.gateserver.service.ArrivalService;
import reactor.core.publisher.Mono;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/arrivals")
public class ArrivalController {

    ArrivalController() {
    }

    @Autowired
    private ArrivalService service;

    /*-
     * curl
     * -H "Content-Type: application/json"
     * -X POST
     * --data '{
     *      "flight":"BA777",
     *      "location":"Oslo",
     *      "time":"2020-07-13T17:02:35.028049",
     *      "gateName":"E"
     * }'
     * "http://localhost:8080/airport-gateserver/arrivals/save"
     */
    @PostMapping(value = "/save")
    public Mono<Void> save( @RequestBody JourneyAndGateName journeyAndGateName) {
        return service.save(journeyAndGateName);
    }

    /*-
     * curl
     * -X GET
     * "http://localhost:8080/airport-gateserver/arrivals/get/A"
     *
     * return
     * {
     * "flight":"LX564",
     * "location":"Arlanda",
     * "time":[2020,8,22,16,23,17,75000000],
     * "gateName":"A"
     * }
     */
    @GetMapping(value = "/get/{gateName}")
    public Mono<JourneyAndGateName> get( @PathVariable("gateName") String gateName) {
        return service.get(gateName);
    }

}
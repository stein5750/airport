package airport.towerserver.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import airport.towerserver.domain.FlightPlan;
import airport.towerserver.service.FlightPlansService;
import reactor.core.publisher.Mono;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/flightplans")
public class FlightPlanController {

    @Autowired
    private FlightPlansService service;

    FlightPlanController() {
    }

    /*-
     * curl
     * -H "Content-Type: application/json"
     * -X POST --data'{
     *      "aircraftId":"65984f6d-f49f-449f-9c8c-bd6b7b62a1fb",
     *      "arrival":{
     *          "flight":"BA777",
     *          "location":"Oslo",
     *          "time":"2020-07-13T17:02:35.028049"
     *      },
     *      "departure":{
     *          "flight":"BA888",
     *          "location":"Arlanda",
     *          "time":"2020-07-13T17:02:35.028049"
     *      }
     * }'
     * "http://localhost:8080/airport-towerserver/flightplans/save"
     */
    @PostMapping(value = "/save")
    public Mono<Void> saveFlightPlan( @RequestBody FlightPlan flightPlan) {
        return service.saveFlightPlan(flightPlan);
    }

    /*-
     * curl
     * -X GET
     * "http://localhost:8080/airport-towerserver/flightplans/retrieve/fc45740a-6493-4a50-8e26-c0b469a0f088"
     *
     * return
     * {
     *      "aircraftId":"fc45740a-6493-4a50-8e26-c0b469a0f088",
     *      "arrival":{
     *          "flight":"LO200",
     *          "location":"St. George's",
     *          "time":[2020,8,22,17,11,33,289000000]
     *      },
     *      "departure":{
     *          "flight":"LO635",
     *          "location":"Asunción",
     *          "time":[2020,8,22,17,12,58,289000000]
     *      }
     * }
     */
    @GetMapping(value = "/retrieve/{aircraftId}")
    public Mono<FlightPlan> retrieveFlightPlan( @PathVariable("aircraftId") UUID aircraftId) {
        return service.getFlightPlan(aircraftId);
    }

}

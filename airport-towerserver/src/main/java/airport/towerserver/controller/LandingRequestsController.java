package airport.towerserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import airport.towerserver.domain.Message;
import airport.towerserver.service.LandingRequestsService;
import reactor.core.publisher.Mono;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/landingRequests")
public class LandingRequestsController {

    @Autowired
    private LandingRequestsService service;

    LandingRequestsController() {
    }

    /*-
     * curl
     * -H "Content-Type: application/json"
     * -X POST --data'{
     *      "aircraftId":"65984f6d-f49f-449f-9c8c-bd6b7b62a1fb",
     *      "text":"flight BA777 requests permission to land."
     * }'
     * "http://localhost:8080/airport-towerserver/landingRequests/save"
     */
    @PostMapping(value = "/save")
    public Mono<Void> saveLandingRequest( @RequestBody Message message) {
        return service.saveLandingRequest(message);
    }

    /*-
     * curl
     * -X GET
     * "http://localhost:8080/airport-towerserver/landingRequests/retrieve"
     *
     * return
     *  {
     *      "aircraftId":"fc45740a-6493-4a50-8e26-c0b469a0f088",
     *      "text":"Flight LO200 requests permission to land."
     *  }
     */
    @GetMapping(value = "/retrieve")
    public Mono<Message> retrieveOneLandingRequest() {
        return service.getLandingRequest();
    }

}

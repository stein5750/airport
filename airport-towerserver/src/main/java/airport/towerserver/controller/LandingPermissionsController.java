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

import airport.towerserver.domain.Message;
import airport.towerserver.service.LandingPermissionsService;
import reactor.core.publisher.Mono;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/landingPermissions")
public class LandingPermissionsController {

    @Autowired
    private LandingPermissionsService service;

    LandingPermissionsController() {
    }

    /*-
     * curl
     * -H "Content-Type: application/json"
     * -X POST
     * --data '{
     *      "aircraftId":"65984f6d-f49f-449f-9c8c-bd6b7b62a1fb",
     *      "text":"E"
     * }'
     * "http://localhost:8080/airport-towerserver/landingPermissions/save"
     */
    @PostMapping(value = "/save")
    public Mono<Void> saveLandingPermission( @RequestBody Message message) {
        return service.saveLandingPermission(message);
    }

    /*-
     * curl
     * -X GET
     * "http://localhost:8080/airport-towerserver/landingPermissions/retrieve/bf32c7d5-dd4b-4d1e-96d8-5a600f146e06"
     *
     * return
     *  {
     *      "aircraftId":"bf32c7d5-dd4b-4d1e-96d8-5a600f146e06",
     *      "text":"C"
     *  }
     */
    @GetMapping(value = "/retrieve/{aircraftId}")
    public Mono<Message> retrieveLandingPermission( @PathVariable("aircraftId") UUID aircraftId) {
        return service.getLandingPermission(aircraftId);
    }

}

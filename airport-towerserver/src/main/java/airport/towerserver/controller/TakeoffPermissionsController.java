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
import airport.towerserver.service.TakeoffPermissionsService;
import reactor.core.publisher.Mono;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/takeoffPermissions")
public class TakeoffPermissionsController {

    @Autowired
    private TakeoffPermissionsService service;

    TakeoffPermissionsController() {
    }

    /*-
     * curl
     * -H "Content-Type: application/json"
     * -X POST --data'{
     *      "aircraftId":"65984f6d-f49f-449f-9c8c-bd6b7b62a1fb",
     *      "text":"Permission granted"
     * }'
     * "http://localhost:8080/airport-towerserver/takeoffPermissions/save"
     */
    @PostMapping(value = "/save")
    public Mono<Void> saveTakeoffPermission( @RequestBody Message message) {
        return service.saveTakeoffPermission(message);
    }

    /*-
     * curl
     *      -X GET
     * "http://localhost:8080/airport-towerserver/takeoffPermissions/retrieve/e7d4d60f-cb4b-46fb-aadf-e5639412f187"
     *
     * return
     *  {
     *      "aircraftId":"e7d4d60f-cb4b-46fb-aadf-e5639412f187",
     *      "text":"Permission granted"}
     */
    @GetMapping(value = "/retrieve/{aircraftId}")
    public Mono<Message> retrieveTakeoffPermission( @PathVariable("aircraftId") UUID aircraftId) {
        return service.getTakeoffPermission(aircraftId);
    }

}

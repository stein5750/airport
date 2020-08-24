package airport.towerserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import airport.towerserver.domain.Message;
import airport.towerserver.service.TakeoffRequestsService;
import reactor.core.publisher.Mono;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/takeoffRequests")
public class TakeoffRequestsController {

    @Autowired
    private TakeoffRequestsService service;

    TakeoffRequestsController() {
    }

    /*-
     * curl
     * -H "Content-Type: application/json"
     * -X POST --data'{"
     *      aircraftId":"65984f6d-f49f-449f-9c8c-bd6b7b62a1fb",
     *      "text":"flight BA777 requests permission to take off."
     * }'
     * "http://localhost:8080/airport-towerserver/takeoffRequests/save"
     */
    @PostMapping(value = "/save")
    public Mono<Void> saveTakeoffRequest( @RequestBody Message message) {
        return service.saveTakeoffRequest(message);
    }

    /*-
     * curl
     * -X GET
     * "http://localhost:8080/airport-towerserver/takeoffRequests/retrieve"
     *
     * return
     *  {
     *      "aircraftId":"411f2f7f-5531-4125-b404-da3b3654e860",
     *      "text":"Flight IB939 requests permission to take off."
     * }
     */
    @GetMapping(value = "/retrieve")
    public Mono<Message> retrieveOneTakeoffRequest() {
        return service.getTakeoffRequest();
    }

}
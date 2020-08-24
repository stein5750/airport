package airport.monitor.server.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import airport.monitor.server.service.MonitorGateNameService;
import reactor.core.publisher.Mono;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/gateNames")
public class MonitorGateNameController {

    MonitorGateNameController() {
    }

    @Autowired
    private MonitorGateNameService service;

    /*-
     * curl
     * -H "Content-Type: application/json"
     * -X POST
     * --data "E"
     * "http://localhost:8080/airport-monitor-server/gateNames/saveGateName"
     */
    @PostMapping(value = "/save", consumes = "application/json")
    public Mono<Void> saveGateName( @RequestBody String gateName) {
        return service.saveGateName(gateName);
    }

    /*
     * curl -X GET
     * "http://localhost:8080/airport-monitor-server/gateNames/getGateNames"
     */
    @GetMapping(value = "/getGateNames", produces = "text/event-stream")
    public Mono<List<String>> getGateList() {
        return service.getGateNames();
    }

    /*
     * curl -H "Content-Type: application/json" -X POST --data "E"
     * "http://localhost:8080/airport-monitor-server/gateNames/delete"
     */
    @PostMapping(value = "/delete", consumes = "application/json")
    public Mono<Void> deleteGateName( @RequestBody String gateName) {
        return service.deleteGateName(gateName);
    }

}

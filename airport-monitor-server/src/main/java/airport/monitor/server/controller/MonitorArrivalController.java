package airport.monitor.server.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import airport.monitor.server.domain.MonitorInfo;
import airport.monitor.server.domain.StatusUpdate;
import airport.monitor.server.service.MonitorArrivalService;
import reactor.core.publisher.Mono;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/arrivals")
public class MonitorArrivalController {

    MonitorArrivalController() {
    }

    @Autowired
    private MonitorArrivalService service;

    /*-
     * curl
     * -H "Content-Type: application/json"
     * -X POST --data '{
     *      "gateName":"E",
     *      "status":"",
     *      "flight":"BA777",
     *      "location":"Oslo",
     *      "time":"2020-07-13T17:02:35.000000"
     * }'
     * "http://localhost:8080/airport-monitor-server/arrivals/save"
     */
    @PostMapping(value = "/save")
    public Mono<Void> save( @RequestBody MonitorInfo monitorInfo) {
        return service.save(monitorInfo);
    }

    /*-
     * curl
     * -X GET
     * "http://localhost:8080/airport-monitor-server/arrivals/get/A"
     *
     * return
     * data:{
     *      "flight":"FR372",
     *      "location":"Riga",
     *      "time":"2020-08-22@16:43:55",
     *      "gateName":"A",
     *      "status":"Disembarking complete"
     * }
     */
    @GetMapping(value = "/get/{gateName}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Mono<MonitorInfo> get( @PathVariable("gateName") String gateName) {
        return service.get(gateName);
    }

    /*-
     * curl
     * -X GET
     * "http://localhost:8080/airport-monitor-server/arrivals/getAll"
     *
     * return
     * [
     *  {
     *      "aircraftId":null,
     *      "flight":"WF821",
     *      "location":"San Marino",
     *      "time":"2020-08-22@16:45:47",
     *      "gateName":"F",
     *      "status":"Disembarking complete"
     *  },
     *  {...},
     *  {...}
     * ]
     */
    @GetMapping(value = "/getAll")
    public Mono<List<MonitorInfo>> getAll() {
        return service.getAll();
    }

    /*
     * curl -H "Content-Type: application/json" -X PUT --data '{ "gateName":"E",
     * "status":"Disembarking" }'
     * "http://localhost:8080/airport-monitor-server/arrivals/status"
     */
    @PutMapping(value = "/status")
    public Mono<Void> updateStatus( @RequestBody StatusUpdate statusUpdate) {
        return service.updateStatus(statusUpdate.getGateName(), statusUpdate.getStatus());
    }

}
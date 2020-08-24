package airport.monitor.server.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import airport.monitor.server.service.MonitorDepartureService;
import reactor.core.publisher.Mono;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/departures")
public class MonitorDepartureController {

    MonitorDepartureController() {
    }

    @Autowired
    private MonitorDepartureService service;

    /*-
     * curl
     * -H "Content-Type: application/json"
     * -X POST
     * --data '{
     *      "gateName":"E",
     *      "status":"",
     *      "flight":"BA777",
     *      "location":"Oslo",
     *      "time":"2020-07-13T17:02:55.000000"
     *      }'
     * "http://localhost:8080/airport-monitor-server/departures/save"
     */
    @PostMapping(value = "/save")
    public Mono<Void> saveMonitorInfo( @RequestBody MonitorInfo monitorInfo) {
        return service.save(monitorInfo);
    }

    /*-
     * curl
     * -X GET
     * "http://localhost:8080/airport-monitor-server/departures/get/A"
     *
     * return
     * {
     *      "flight":"LPA597",
     *      "location":"Tashkent",
     *      "time":"2020-08-22@16:57:58",
     *      "gateName":"A",
     *      "status":"Gate closed"
     * }
     */
    @GetMapping(value = "/get/{gateName}")
    public Mono<MonitorInfo> get( @PathVariable("gateName") String gateName) {
        return service.get(gateName);
    }

    /*-
     * curl
     * -X GET
     * "http://localhost:8080/airport-monitor-server/departures/getAll"
     *
     * return
     * [
     *  {
     *      "aircraftId":null,
     *      "flight":"WF821",
     *      "location":"Copenhagen",
     *      "time":"2020-08-22@17:45:00",
     *      "gateName":"F",
     *      "status":"Boarding complete"
     *  },
     *  {...},
     *  {...}
     * ]
     */
    @GetMapping(value = "/getAll")
    public Mono<List<MonitorInfo>> getAll() {
        return service.getAll();
    }

    /*-
     * curl
     * -H "Content-Type: application/json"
     * -X PUT --data '{
     *      "gateName":"E",
     *      "status":"Boarding"
     * }'
     * "http://localhost:8080/airport-monitor-server/departures/status"
     */
    @PutMapping(value = "/status")
    public Mono<Void> updateStatus( @RequestBody StatusUpdate statusUpdate) {
        String gateName = statusUpdate.getGateName();
        String status = statusUpdate.getStatus();
        return service.updateStatus(gateName, status);
    }

}
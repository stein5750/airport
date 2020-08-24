package airport.gates.app;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import airport.gates.domain.JourneyAndGateName;
import airport.gates.gateclient.service.GateArrivalService;
import airport.gates.monitorclient.domain.MonitorInfo;
import airport.gates.monitorclient.service.MonitorDepartureService;

@Component
@Scope("prototype")
public class DepartureRoutine implements Runnable {

    @Autowired
    MonitorDepartureService monitorDepartureService;

    @Autowired
    GateArrivalService gateService;

    private static Logger log = LoggerFactory.getLogger(DepartureRoutine.class);

    boolean isRunning;
    private Integer speedFactor = 35;
    private String status = "";
    private String gateName;
    private JourneyAndGateName departure;

    public void setGateName( String gateName) {
        this.gateName = gateName;
    }

    public void setDeparture( JourneyAndGateName departure) {
        this.departure = departure;
    }

    @Override
    public void run() {
        // Create MonitorInfo object
        MonitorInfo monitorInfo = new MonitorInfo();
        monitorInfo.setFlight(departure.getFlight());
        monitorInfo.setGateName(departure.getGateName());
        monitorInfo.setLocation(departure.getLocation());
        monitorInfo.setTime(departure.getTime());
        monitorInfo.setStatus(status);
        // inform airport-monitor-webserver
        monitorDepartureService.save(monitorInfo);

        // goToGate 20 minutes before departure
        LocalDateTime goToGateTime = departure.getTime().minusSeconds(1200 / speedFactor);
        // boardingTime 15 minutes before departure
        LocalDateTime boardingTime = departure.getTime().minusSeconds(900 / speedFactor);
        // gate closed 5 minutes before departure
        LocalDateTime gateClosedTime = departure.getTime().minusSeconds(300 / speedFactor);

        isRunning = true;

        while (isRunning) {

            if (LocalDateTime.now().isAfter(goToGateTime) && LocalDateTime.now().isBefore(boardingTime)
                    && !status.equals("Go to gate")) {
                status = "Go to gate";
                monitorDepartureService.updateMonitorInfoStatus(gateName, status);
            } else if (LocalDateTime.now().isAfter(boardingTime) && LocalDateTime.now().isBefore(gateClosedTime)
                    && !status.equals("Boarding")) {
                status = "Boarding";
                monitorDepartureService.updateMonitorInfoStatus(gateName, status);
            } else if (LocalDateTime.now().isAfter(gateClosedTime)) {
                status = "Gate closed";
                monitorDepartureService.updateMonitorInfoStatus(gateName, status);
                // -- TODO stop
                isRunning = false;
            }

            try {
                Thread.sleep(10000 / speedFactor); // sleep 10 seconds/speedFactor
            } catch (InterruptedException e) {
                log.error(e.getMessage());
            }

        }
    }
    // --TODO implement stop
}

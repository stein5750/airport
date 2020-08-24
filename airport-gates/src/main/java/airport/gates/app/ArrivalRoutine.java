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
import airport.gates.monitorclient.service.MonitorArrivalService;

@Component
@Scope("prototype")
public class ArrivalRoutine implements Runnable {

    @Autowired
    GateArrivalService gateService;

    @Autowired
    MonitorArrivalService monitorArrivalService;

    private static Logger log = LoggerFactory.getLogger(ArrivalRoutine.class);

    boolean isRunning;
    private Integer speedFactor = 35;
    private String status = "";
    private String gateName;
    private JourneyAndGateName arrival;

    public void setGateName( String gateName) {
        this.gateName = gateName;
    }

    public void setArrival( JourneyAndGateName arrival) {
        this.arrival = arrival;
    }

    @Override
    public void run() {
        // Create MonitorInfo object
        MonitorInfo monitorInfo = new MonitorInfo();
        monitorInfo.setFlight(arrival.getFlight());
        monitorInfo.setGateName(arrival.getGateName());
        monitorInfo.setLocation(arrival.getLocation());
        monitorInfo.setTime(arrival.getTime());
        monitorInfo.setStatus(status);
        // inform airport-monitor-webserver
        monitorArrivalService.save(monitorInfo);

        // Landed 5 minutes after arrival
        LocalDateTime landingTime = arrival.getTime().plusSeconds(300 / speedFactor);
        // Disembarking 10 minutes after arrival
        LocalDateTime disembarkingTime = arrival.getTime().plusSeconds(600 / speedFactor);
        // Disembarking complete 20 minutes after arrival
        LocalDateTime disembarkingCompleteTime = arrival.getTime().plusSeconds(1200 / speedFactor);

        isRunning = true;

        while (isRunning) {

            if (LocalDateTime.now().isAfter(landingTime) && LocalDateTime.now().isBefore(disembarkingTime)
                    && !status.equals("Landed")) {
                status = "Landed";
                monitorArrivalService.updateMonitorInfoStatus(gateName, status);
            }

            else if (LocalDateTime.now().isAfter(disembarkingTime)
                    && LocalDateTime.now().isBefore(disembarkingCompleteTime) && !status.equals("Disembarking")) {
                status = "Disembarking";
                monitorArrivalService.updateMonitorInfoStatus(gateName, status);
            } else if (LocalDateTime.now().isAfter(disembarkingCompleteTime)) {
                status = "Disembarking complete";
                monitorArrivalService.updateMonitorInfoStatus(gateName, status);
                // stop
                isRunning = false;
            }

            try {
                Thread.sleep(10000 / speedFactor); // sleep 10 seconds/speedFactor
            } catch (InterruptedException e) {
                log.error(e.getMessage());
            }

        }
    }

    public void stop() {
        // --TODO stop is deprecated.
    }
}

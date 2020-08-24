package airport.aircrafts.app;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import airport.aircrafts.domain.FlightPlan;
import airport.aircrafts.domain.Message;
import airport.aircrafts.towerclient.service.AircraftService;

@Component
@Scope("prototype")
public class AircraftDepartureProcedure {

    @Autowired
    private AircraftService service;

    private static Logger log = LoggerFactory.getLogger(AircraftDepartureProcedure.class);

    public AircraftDepartureProcedure() {
    }

    public void depart( UUID aircraftId, FlightPlan flightPlan) {
        log.info("Aircraft with id " + aircraftId + " requesting TakeoffPermission.");
        String text = "Flight " + flightPlan.getArrival().getFlight() + " requests permission to take off.";
        service.requestTakeoff(aircraftId, text);
        waitForTakeoffPermission(aircraftId);
        log.info("Aircraft with id " + aircraftId + " got takeoffpermission.");
        return;

    }

    private boolean waitForTakeoffPermission( UUID aircraftId) {
        Message message;
        boolean takeoffPermissionGranted = false;
        while (takeoffPermissionGranted == false) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                log.error(e.getMessage());
            }
            message = service.getTakeoffPermission(aircraftId);
            if (message != null && message.getText().equals("Permission granted")) {
                takeoffPermissionGranted = true;
            }
        }
        return true;
    }
}

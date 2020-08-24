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
public class AircraftArrivalProcedure {

    @Autowired
    private AircraftService service;

    private static Logger log = LoggerFactory.getLogger(AircraftArrivalProcedure.class);

    public AircraftArrivalProcedure() {
    }

    public void arrive( UUID aircraftId, FlightPlan flightPlan) {

        log.info("Aircraft with id " + aircraftId + " sending flightPlan to tower.");
        service.sendFlightPlan(flightPlan);
        // Request landingpermission
        log.info("Aircraft with id " + aircraftId + " requesting LandingPermission.");
        String text = "Flight " + flightPlan.getArrival().getFlight() + " requests permission to land.";
        service.requestLanding(aircraftId, text);
        String gate = getLandingPermission(aircraftId);
        log.info("Aircraft with id " + aircraftId + " got landingpermission for gate:" + gate + ".");
    }

    private String getLandingPermission( UUID aircraftId) {
        Message landingPermission = null;
        while (landingPermission == null) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                log.error(e.getMessage());
            }

            landingPermission = service.getLandingPermission(aircraftId);
        }
        return landingPermission.getText();
    }
}

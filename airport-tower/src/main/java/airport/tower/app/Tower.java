package airport.tower.app;

import java.util.HashMap;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import airport.tower.aircraft.domain.FlightPlan;

@Component
public class Tower {

    @Autowired
    ArrivalRoutine arrivalRoutine;

    @Autowired
    DepartureRoutine departureRoutine;

    HashMap<UUID, String> aircraftAtGate = new HashMap<UUID, String>();

    private static Logger log = LoggerFactory.getLogger(Tower.class);

    public Tower() {
    }

    public void directTraffic() {

        log.info("Tower is running");

        while (true) {

            arrivals();
            departures();
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                log.error(e.getMessage());
            }
        }
    }

    /*
     * Procedure for arriving aircrafts
     */
    private void arrivals() {

        UUID aircraftId = arrivalRoutine.getAircraftId();
        if (aircraftId != null) {
            String gateName = arrivalRoutine.getAvailableGate();
            if (gateName != null) {
                // mark gateName as not available
                arrivalRoutine.setGateUnavailable(gateName);
                // remove aircraft from arrival que
                arrivalRoutine.removeAircraftFromQue(aircraftId);
                // get FlightPlan for aircraft
                FlightPlan flightPlan = arrivalRoutine.getFlightPlan(aircraftId);
                // inform gate about the aircrafts planned arrival
                arrivalRoutine.informGateAboutArrival(gateName, flightPlan.getArrival());
                // inform gate about the aircrafts planned departure
                departureRoutine.informGateAboutDeparture(gateName, flightPlan.getDeparture());
                // give landingpermission
                arrivalRoutine.giveLandingPermission(aircraftId, gateName);
                // Remember which aircraft is assigned to which gate
                aircraftAtGate.put(aircraftId, gateName);
            }
        }
    }

    /*
     * Procedure for departing aircrafts
     */
    private void departures() {
        UUID aircraftId = departureRoutine.getAircraftId();
        if (aircraftId != null) {
            // mark gateName as available
            String gateName = aircraftAtGate.remove(aircraftId);
            departureRoutine.setGateAvailable(gateName);
            // give takeoff permission
            departureRoutine.giveTakeoffPermission(aircraftId);
        }
    }

}
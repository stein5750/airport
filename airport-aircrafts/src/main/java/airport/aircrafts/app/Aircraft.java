package airport.aircrafts.app;

import java.time.LocalDateTime;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import airport.aircrafts.domain.FlightPlan;
import airport.aircrafts.util.FilePropertyReader;

@Component
@Scope("prototype")
public class Aircraft implements Runnable {

    boolean isRunning = true;
    private FlightPlan flightPlan;

    private Integer speedFactor;
    private UUID aircraftId;
    private FilePropertyReader properties;

    @Autowired
    private AircraftArrivalProcedure arrivalProcedure;
    @Autowired
    private AircraftDepartureProcedure departureProcedure;

    private static Logger log = LoggerFactory.getLogger(Aircraft.class);

    public Aircraft() {
        properties = new FilePropertyReader();
        speedFactor = properties.getSpeedFactor();
    }

    public UUID getAircraftId() {
        return aircraftId;
    }

    public void setAircraftId( UUID aircraftId) {
        this.aircraftId = aircraftId;
    }

    public FlightPlan getFlightPlan() {
        return flightPlan;
    }

    public void setFlightPlan( FlightPlan flightPlan) {
        this.flightPlan = flightPlan;
    }

    @Override
    public void run() {

        Boolean arriving = true;
        Boolean departing = false;

        while (isRunning) {
            // if it's less than five minutes to arrival, then ask for landing permission.
            if (arriving == true
                    && LocalDateTime.now().isAfter(flightPlan.getArrival().getTime().minusSeconds(300 / speedFactor))) {
                arrivalProcedure.arrive(aircraftId, flightPlan);
                arriving = false;
                departing = true;
            }

            // if it's less than five minutes to departure, then ask for takeoff permission.
            if (departing == true && LocalDateTime.now()
                    .isAfter(flightPlan.getDeparture().getTime().minusSeconds(300 / speedFactor))) {
                departureProcedure.depart(aircraftId, flightPlan);
                departing = false;
                stop();
            }

            try {
                // sleep for one minute
                Thread.sleep(60000 / speedFactor);
            } catch (InterruptedException e) {
                log.error(e.getMessage());
            }
        }
    }

    public void stop() {
        log.info("Aircraft with id " + aircraftId + " has departed.");
        isRunning = false;
        return;
    }
}

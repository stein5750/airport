package airport.gates.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import airport.gates.domain.JourneyAndGateName;
import airport.gates.gateclient.service.AvailabilityService;
import airport.gates.gateclient.service.GateArrivalService;
import airport.gates.gateclient.service.GateDepartureService;
import reactor.core.publisher.Mono;

@Component
@Scope("prototype")
public class Gate implements Runnable {

    private boolean isRunning;

    private String gateName;

    @Autowired
    AnnotationConfigApplicationContext applicationContext;

    @Autowired
    private AvailabilityService availabilityService;

    @Autowired
    private GateArrivalService gateArrivalService;

    @Autowired
    private GateDepartureService gateDepartureService;

    private static Logger log = LoggerFactory.getLogger(Gate.class);

    public Gate() {
    }

    public String getGateName() {
        return gateName;
    }

    public void setGateName( String gateName) {
        this.gateName = gateName;
    }

    @Override
    public void run() {
        log.info("Gate: " + gateName + " is running");

        isRunning = true;
        while (isRunning) {

            Mono<JourneyAndGateName> monoArrival = gateArrivalService.get(gateName);
            // Get and remove next Departure for this gate
            Mono<JourneyAndGateName> monoDeparture = gateDepartureService.get(gateName);

            monoArrival.subscribe(arrival -> handleArrival(arrival));
            monoDeparture.subscribe(departure -> handleDeparture(departure));

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                log.error(e.getMessage());
            }
        }
        return;
    }

    private void handleArrival( JourneyAndGateName arrival) {
        ArrivalRoutine arrivalRoutine = (ArrivalRoutine) applicationContext.getBean("arrivalRoutine");
        arrivalRoutine.setGateName(gateName);
        arrivalRoutine.setArrival(arrival);
        Thread arrivalRoutineThread = new Thread(arrivalRoutine);
        arrivalRoutineThread.start();
    }

    private void handleDeparture( JourneyAndGateName departure) {
        DepartureRoutine departureRoutine = (DepartureRoutine) applicationContext.getBean("departureRoutine");
        departureRoutine.setGateName(gateName);
        departureRoutine.setDeparture(departure);
        Thread departureRoutineThread = new Thread(departureRoutine);
        departureRoutineThread.start();
    }

    public void stop() { // --TODO depracted
        isRunning = false;
        availabilityService.saveAvailability(gateName, false);
        // --TODO implement stop of child threads
    }

}

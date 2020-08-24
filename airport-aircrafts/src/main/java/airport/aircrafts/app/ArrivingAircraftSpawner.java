package airport.aircrafts.app;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import airport.aircrafts.domain.FlightPlan;
import airport.aircrafts.domain.Journey;
import airport.aircrafts.util.FilePropertyReader;

@Component
public class ArrivingAircraftSpawner {

    @Autowired
    AnnotationConfigApplicationContext applicationContext;

    private static Logger log = LoggerFactory.getLogger(ArrivingAircraftSpawner.class);

    private FilePropertyReader filePropertyReader;

    private String[] capitals;
    private String[] airlinecodes;
    private int timeSpentOnTarmac = 3000; // time in seconds beween landing and departure
    private Integer speedFactor;
    private int numberOfGates;

    public ArrivingAircraftSpawner() {
        filePropertyReader = new FilePropertyReader();
        // get properties from file
        capitals = filePropertyReader.getCapitals();
        airlinecodes = filePropertyReader.getAirlineCodes();
        speedFactor = filePropertyReader.getSpeedFactor();
        // spawn some initial aircrafts to get started
        numberOfGates = filePropertyReader.getGateNames().size();
    }

    public void spawnAircrafts() {
//		spawning initial aircrafts...
//		for( int i = 0; i < numberOfGates-1 ; i++) {
//			spawnAircraft();
//		}

        while (true) {
            spawnAircraft();
            try {
                Thread.sleep(timeSpentOnTarmac / numberOfGates / speedFactor * 1000); // sleep
            } catch (InterruptedException e) {
                log.error(e.getMessage());
            }

        }
    }

    // return random integer
    private int randomInt( int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }

    private String getRandomAirlineCode() {
        return airlinecodes[randomInt(0, airlinecodes.length - 1)];
    }

    private String getRandomFlightNumber( String airlineCode) {
        // random airlinecode+flightnumber
        return airlineCode + randomInt(100, 999);
    }

    private String getRandomLocation() {
        return capitals[randomInt(0, capitals.length - 1)];
    }

    private FlightPlan generateFlightPlan() {
        // generate airlinecode identifying the airline company
        String airlineCode = getRandomAirlineCode();

        // Generate arrival
        String arrivingFlight = getRandomFlightNumber(airlineCode);
        String origin = getRandomLocation();
        LocalDateTime arrivalTime = LocalDateTime.now().plusSeconds(1200 / speedFactor); // schedule arrival in 20
                                                                                         // minutes
        Journey arrival = new Journey();
        arrival.setFlight(arrivingFlight);
        arrival.setLocation(origin);
        arrival.setTime(arrivalTime);

        // Generate departure
        String departingFlight = getRandomFlightNumber(airlineCode);
        String destination = getRandomLocation();
        LocalDateTime departureTime = arrivalTime.plusSeconds(timeSpentOnTarmac / speedFactor); // schedule departure 35
                                                                                                // minutes after arrival
        Journey departure = new Journey();
        departure.setFlight(departingFlight);
        departure.setLocation(destination);
        departure.setTime(departureTime);

        // Generate flightplan
        FlightPlan flightPlan = new FlightPlan();
        flightPlan.setArrival(arrival);
        flightPlan.setDeparture(departure);

        // return flightplan
        return flightPlan;
    }

    public Aircraft createAircraft() {
        FlightPlan flightPlan = generateFlightPlan();
        Aircraft aircraft = (Aircraft) applicationContext.getBean("aircraft");
        UUID aircraftId = UUID.randomUUID();
        aircraft.setAircraftId(aircraftId);
        flightPlan.setAircraftId(aircraftId);
        aircraft.setFlightPlan(flightPlan);
        return aircraft;
    }

    public void spawnAircraft() {
        Aircraft aircraft = createAircraft();
        Thread aircraftThread = new Thread(aircraft);
        aircraftThread.start();
        log.info("Aircraft with id " + aircraft.getAircraftId() + " created.");
    }

}
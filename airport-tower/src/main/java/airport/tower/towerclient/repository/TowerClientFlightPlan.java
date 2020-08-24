package airport.tower.towerclient.repository;

import java.util.UUID;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;

import airport.tower.aircraft.domain.FlightPlan;

@Repository("flightPlansClient")
public class TowerClientFlightPlan {

    private WebClient client;

    public TowerClientFlightPlan() {
        client = WebClient.create("http://localhost:8080/airport-towerserver/flightplans");
    }

    public FlightPlan getFlightPlan( UUID aircraftId) {
        return client.get().uri("/retrieve/" + aircraftId.toString()).accept(MediaType.APPLICATION_JSON).retrieve()
                .bodyToMono(FlightPlan.class).block(); // --TODO rewrite
    }

}
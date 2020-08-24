package airport.aircrafts.towerclient.repository;

import java.util.UUID;

import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import airport.aircrafts.domain.FlightPlan;
import airport.aircrafts.domain.Message;

public class AircraftClient {

    private WebClient webClient;

    public AircraftClient() {
        webClient = WebClient.create("http://localhost:8080/airport-towerserver");
    }

    public void saveFlightPlan( FlightPlan flightPlan) {
        webClient.post().uri("/flightplans/save").accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(flightPlan)).retrieve().bodyToMono(Void.class).subscribe();
    }

    public void requestLanding( Message message) {
        webClient.post().uri("/landingRequests/save").accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(message)).retrieve().bodyToMono(Void.class).subscribe();
    }

    public Message getLandingPermission( UUID aircraftId) {
        return webClient.get().uri("/landingPermissions/retrieve/" + aircraftId.toString())
                .accept(MediaType.APPLICATION_JSON).retrieve().bodyToMono(Message.class).block(); // --TODO rewrite
    }

    public void requestTakeoff( Message message) {
        webClient.post().uri("/takeoffRequests/save").accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(message)).retrieve().bodyToMono(Void.class).subscribe();
    }

    public Message getTakeoffPermission( UUID aircraftId) {
        return webClient.get().uri("/takeoffPermissions/retrieve/" + aircraftId.toString())
                .accept(MediaType.APPLICATION_JSON).retrieve().bodyToMono(Message.class).block(); // --TODO rewrite
    }

}

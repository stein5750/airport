package airport.tower.towerclient.repository;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;

import airport.tower.aircraft.domain.Message;

@Repository
public class TowerClientLandingRequest {

    private WebClient client;

    public TowerClientLandingRequest() {
        client = WebClient.create("http://localhost:8080/airport-towerserver");
    }

    public Message retrieveLandingRequest() {
        return client.get().uri("/landingRequests/retrieve").accept(MediaType.APPLICATION_JSON).retrieve()
                .bodyToMono(Message.class).block(); // --TODO rewrite
    }

}
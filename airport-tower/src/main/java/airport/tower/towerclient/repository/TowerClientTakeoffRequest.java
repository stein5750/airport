package airport.tower.towerclient.repository;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;

import airport.tower.aircraft.domain.Message;

@Repository("takeoffRequestsClient")
public class TowerClientTakeoffRequest {

    private WebClient client;

    public TowerClientTakeoffRequest() {
        client = WebClient.create("http://localhost:8080/airport-towerserver/takeoffRequests/");
    }

    public Message retrieveTakeoffRequest() {
        return client.get().uri("/retrieve").accept(MediaType.APPLICATION_JSON).retrieve().bodyToMono(Message.class)
                .block(); // --TODO rewrite
    }

}
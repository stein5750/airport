package airport.tower.towerclient.repository;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import airport.tower.aircraft.domain.Message;

@Repository
public class TowerClientTakeoffPermission {

    private WebClient client;

    public TowerClientTakeoffPermission() {
        client = WebClient.create("http://localhost:8080/airport-towerserver/takeoffPermissions");
    }

    public void saveTakeoffPermission( Message message) {
        client.post().uri("/save").accept(MediaType.APPLICATION_JSON).body(BodyInserters.fromValue(message)).retrieve()
                .bodyToMono(Void.class).subscribe();
    }

}
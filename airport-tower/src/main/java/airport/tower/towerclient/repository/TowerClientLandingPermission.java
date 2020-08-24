package airport.tower.towerclient.repository;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import airport.tower.aircraft.domain.Message;

@Repository
public class TowerClientLandingPermission {

    private WebClient client;

    public TowerClientLandingPermission() {
        client = WebClient.create("http://localhost:8080/airport-towerserver/landingPermissions");
    }

    public void saveLandingPermission( Message message) {
        client.post().uri("/save").accept(MediaType.APPLICATION_JSON).body(BodyInserters.fromValue(message)).retrieve()
                .bodyToMono(Void.class).subscribe();
    }

}
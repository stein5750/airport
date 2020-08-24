package airport.gates.monitorclient.repository;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

@Repository
public class MonitorGateNameClient {

    private WebClient client;

    public MonitorGateNameClient() {
        client = WebClient.create("http://localhost:8080/airport-monitor-server/gateNames");
    }

    public void saveGateName( String gateName) {
        client.post().uri("/save").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(gateName)).retrieve().bodyToMono(Void.class).subscribe();
    }

    public void deleteGateName( String gateName) {
        client.post().uri("/delete").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(gateName)).retrieve().bodyToMono(Void.class).subscribe();
    }

}
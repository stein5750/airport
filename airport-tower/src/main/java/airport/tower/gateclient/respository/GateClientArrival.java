package airport.tower.gateclient.respository;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import airport.tower.gateclient.domain.JourneyAndGateName;

@Repository
public class GateClientArrival {

    private WebClient client;

    public GateClientArrival() {
        client = WebClient.create("http://localhost:8080/airport-gateserver/arrivals");
    }

    public void saveArrival( JourneyAndGateName journeyAndGateName) {
        client.post().uri("/save").accept(MediaType.APPLICATION_JSON).body(BodyInserters.fromValue(journeyAndGateName))
                .retrieve().bodyToMono(Void.class).subscribe();
    }

//    public void removeGateInformation( String gateName) {
//        client.post().uri("/remove").accept(MediaType.APPLICATION_JSON)
//                .body(BodyInserters.fromPublisher(Mono.just(gateName), String.class));
//    }
    public void removeGateInformation( String gateName) {
        client.post().uri("/remove").accept(MediaType.APPLICATION_JSON).body(BodyInserters.fromValue(gateName))
                .retrieve().bodyToMono(Void.class).subscribe();
    }
}
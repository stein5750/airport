package airport.gates.gateclient.repository;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;

import airport.gates.domain.JourneyAndGateName;
import reactor.core.publisher.Mono;

@Repository
public class GateDepartureClient {

    private WebClient client;

    public GateDepartureClient() {
        client = WebClient.create("http://localhost:8080/airport-gateserver/departures");
    }

    public Mono<JourneyAndGateName> get( String gateName) {
        return client.get().uri("/get/" + gateName).accept(MediaType.APPLICATION_JSON).retrieve()
                .bodyToMono(JourneyAndGateName.class);
    }

}
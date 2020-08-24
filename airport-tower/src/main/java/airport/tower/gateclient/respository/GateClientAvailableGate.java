package airport.tower.gateclient.respository;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;

import airport.tower.gateclient.domain.GateAvailability;

@Repository
public class GateClientAvailableGate {

    private WebClient webClient;

    public GateClientAvailableGate() {
        webClient = WebClient.create("http://localhost:8080/airport-gateserver/gates");
    }

    public String getAvailableGate() {
        return webClient.get().uri("/getAvailableGate").accept(MediaType.APPLICATION_JSON).retrieve()
                .bodyToMono(GateAvailability.class).map(result -> result.getGateName()).block(); // --TODO rewrite
    }

    public void saveAvailability( String gateName, boolean available) {
        webClient.get().uri("/save/" + gateName + "/" + available).accept(MediaType.APPLICATION_JSON).retrieve()
                .bodyToMono(Void.class).subscribe();
    }
}
package airport.gates.monitorclient.repository;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import airport.gates.monitorclient.domain.MonitorInfo;
import airport.gates.monitorclient.domain.StatusUpdate;

@Repository
public class MonitorArrivalClient {

    private WebClient client;

    public MonitorArrivalClient() {
        client = WebClient.create("http://localhost:8080/airport-monitor-server/arrivals");
    }

    public void save( MonitorInfo monitorInfo) {
        client.post().uri("/save").accept(MediaType.APPLICATION_JSON).body(BodyInserters.fromValue(monitorInfo))
                .retrieve().bodyToMono(Void.class).subscribe();
    }

    public void updateMonitorInfoStatus( StatusUpdate statusUpdate) {
        client.put().uri("/status").accept(MediaType.APPLICATION_JSON).body(BodyInserters.fromValue(statusUpdate))
                .retrieve().bodyToMono(Void.class).subscribe();
    }

}
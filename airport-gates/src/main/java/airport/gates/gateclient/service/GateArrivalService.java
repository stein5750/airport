package airport.gates.gateclient.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import airport.gates.domain.JourneyAndGateName;
import airport.gates.gateclient.repository.GateArrivalClient;
import reactor.core.publisher.Mono;

@Service
public class GateArrivalService {

    @Autowired
    GateArrivalClient client;

    public Mono<JourneyAndGateName> get( String gateName) {
        return client.get(gateName);
    }

}

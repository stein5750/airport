package airport.gateserver.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import airport.gateserver.domain.JourneyAndGateName;
import airport.gateserver.repository.ArrivalRepository;
import reactor.core.publisher.Mono;

@Service
public class ArrivalService {

    @Autowired
    private ArrivalRepository repository;

    public Mono<Void> save( JourneyAndGateName journeyAndGateName) {
        return repository.save(journeyAndGateName);
    }

    public Mono<JourneyAndGateName> get( String gateName) {
        return repository.get(gateName);
    }

}

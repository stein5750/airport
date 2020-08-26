package airport.gateserver.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mongodb.client.result.DeleteResult;

import airport.gateserver.domain.JourneyAndGateName;
import airport.gateserver.repository.DepartureRepository;
import reactor.core.publisher.Mono;

@Service
public class DepartureService {

    @Autowired
    private DepartureRepository repository;

    public Mono<Void> save( JourneyAndGateName journeyAndGateName) {
        return repository.save(journeyAndGateName);
    }

    public Mono<JourneyAndGateName> get( String gateName) {
        return repository.get(gateName);
    }

    public Mono<DeleteResult> remove( String gateName) {
        return repository.remove(gateName);
    }

}
package airport.towerserver.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import airport.towerserver.domain.Message;
import airport.towerserver.repository.LandingRequestsRepository;
import reactor.core.publisher.Mono;

@Service
public class LandingRequestsService {

    @Autowired
    private LandingRequestsRepository repository;

    public Mono<Void> saveLandingRequest( Message message) {
        return repository.saveLandingRequest(message);
    }

    public Mono<Message> getLandingRequest() {
        return repository.getLandingRequest();
    }

}
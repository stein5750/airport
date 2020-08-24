package airport.towerserver.service;

import reactor.core.publisher.Mono;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import airport.towerserver.domain.Message;
import airport.towerserver.repository.TakeoffRequestsRepository;

@Service
public class TakeoffRequestsService {
	
	
	@Autowired
	private TakeoffRequestsRepository repository;

	
	public Mono<Void> saveTakeoffRequest( Message message) {
		return repository.saveTakeoffRequest( message);
	}

	
	public Mono<Message> getTakeoffRequest() {
		return repository.getTakeoffRequest();
	}		
	
}
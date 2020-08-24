package airport.towerserver.service;

import reactor.core.publisher.Mono;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import airport.towerserver.domain.Message;
import airport.towerserver.repository.TakeoffPermissionsRepository;

@Service
public class TakeoffPermissionsService {
		

	@Autowired
	private TakeoffPermissionsRepository repository;		
	
		
	public Mono<Void> saveTakeoffPermission( Message message) {
		return repository.saveTakeoffPermission( message);
	}
	
	public Mono<Message> getTakeoffPermission( UUID aircraftId) {
		return repository.getTakeoffPermission( aircraftId);
	}	
	
}
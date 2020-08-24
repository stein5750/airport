package airport.towerserver.service;

import reactor.core.publisher.Mono;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import airport.towerserver.domain.Message;
import airport.towerserver.repository.LandingPermissionsRepository;

@Service
public class LandingPermissionsService {
		
	@Autowired
	private LandingPermissionsRepository repository;	
	
	
	public Mono<Void> saveLandingPermission( Message message) {
		return repository.saveLandingPermission( message);
	}	
	
	
	public Mono<Message> getLandingPermission(UUID id) {
		return repository.getLandingPermission( id);
	}
	

}
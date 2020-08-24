package airport.monitor.server.service;

import reactor.core.publisher.Mono;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import airport.monitor.server.repository.GateNameRepository;

@Service
public class MonitorGateNameService {

		
	@Autowired
	private GateNameRepository repository;
	
	
	public Mono<Void> saveGateName( String gateName) {
		return repository.saveGateName( gateName);
	}
	
	
	public Mono<List<String>> getGateNames(){
		return repository.getGateNames();
	}
	

	public Mono<Void> deleteGateName( String gateName) {
		return repository.deleteGateName( gateName);
	}		
	
}
package airport.gates.gateclient.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import airport.gates.gateclient.repository.AvailabilityClient;

@Service
public class AvailabilityService {
	
	@Autowired
	private AvailabilityClient client;
	
	public void saveAvailability(String gateName, boolean available) {
		client.saveAvailability(gateName, available);
	};

}

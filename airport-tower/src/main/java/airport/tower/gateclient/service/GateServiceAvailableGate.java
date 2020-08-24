package airport.tower.gateclient.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import airport.tower.gateclient.respository.GateClientAvailableGate;

@Service
public class GateServiceAvailableGate {

	@Autowired
	GateClientAvailableGate client;
	
	public GateServiceAvailableGate() {}
	
	public String getAvailableGate() {
		return client.getAvailableGate();
	}
	
	public void saveAvailability(String gateName, boolean available) {
		client.saveAvailability(gateName, available);
	};
}
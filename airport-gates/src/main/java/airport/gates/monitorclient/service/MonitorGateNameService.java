package airport.gates.monitorclient.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import airport.gates.monitorclient.repository.MonitorGateNameClient;


@Service
public class MonitorGateNameService {
	
	@Autowired
	private MonitorGateNameClient client;
	
	
	public void saveGateName( String gateName) {
		client.saveGateName( gateName);
	}	

	public void deleteGateName( String gateName) {
		client.deleteGateName( gateName);
	}	

}
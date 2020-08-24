package airport.gates.monitorclient.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import airport.gates.monitorclient.domain.MonitorInfo;
import airport.gates.monitorclient.domain.StatusUpdate;
import airport.gates.monitorclient.repository.MonitorDepartureClient;

@Service
public class MonitorDepartureService {
	
	@Autowired
	private MonitorDepartureClient client;
		
	
	public void save( MonitorInfo monitorInfo) {
		client.save( monitorInfo);
	}	
	
	
	public void updateMonitorInfoStatus(String gateName, String status) {
		StatusUpdate statusUpdate = new StatusUpdate();
		statusUpdate.setGateName(gateName);
		statusUpdate.setStatus(status);
		client.updateMonitorInfoStatus( statusUpdate);
	}
}
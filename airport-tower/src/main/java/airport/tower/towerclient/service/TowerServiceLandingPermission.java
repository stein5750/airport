package airport.tower.towerclient.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import airport.tower.aircraft.domain.Message;
import airport.tower.towerclient.repository.TowerClientLandingPermission;

@Service("landingPermissionsClient")
public class TowerServiceLandingPermission {

	@Autowired
	TowerClientLandingPermission client;
	
	public TowerServiceLandingPermission() {}
	

	public void saveLandingPermission(UUID aircraftId, String gateName) {		
		Message message = new Message();
		message.setAircraftId(aircraftId);
		message.setText(gateName);
		client.saveLandingPermission(message);
	}
	
}
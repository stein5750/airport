package airport.tower.towerclient.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import airport.tower.aircraft.domain.Message;
import airport.tower.towerclient.repository.TowerClientTakeoffPermission;

@Service("takeoffPermissionsService")
public class TowerServiceTakeoffPermission {

	@Autowired
	TowerClientTakeoffPermission client;
	
	public TowerServiceTakeoffPermission() {}
	

	public void saveTakeoffPermission(UUID aircraftId, String text) {		
		Message message = new Message();
		message.setAircraftId(aircraftId);
		message.setText(text);
		client.saveTakeoffPermission( message);
	}
	
}
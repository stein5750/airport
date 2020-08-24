package airport.tower.towerclient.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import airport.tower.aircraft.domain.Message;
import airport.tower.towerclient.repository.TowerClientTakeoffRequest;

@Service("takeoffRequestsService")
public class TowerServiceTakeoffRequest {

	@Autowired
	TowerClientTakeoffRequest client;
	
	public TowerServiceTakeoffRequest() {}
	
	public Message retrieveTakeoffRequest() {
		return client.retrieveTakeoffRequest();
	}
	
}
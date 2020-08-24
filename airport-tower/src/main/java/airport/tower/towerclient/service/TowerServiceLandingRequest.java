package airport.tower.towerclient.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import airport.tower.aircraft.domain.Message;
import airport.tower.towerclient.repository.TowerClientLandingRequest;

@Service("landingRequestsService")
public class TowerServiceLandingRequest {

	@Autowired
	TowerClientLandingRequest client;
	
	public TowerServiceLandingRequest() {}
	
	public Message getLandingRequest() {
		return client.retrieveLandingRequest();
	}
	
}
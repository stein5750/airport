package airport.tower.towerclient.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import airport.tower.aircraft.domain.FlightPlan;
import airport.tower.towerclient.repository.TowerClientFlightPlan;

@Service
public class TowerServiceFlightPlan {

	@Autowired
	TowerClientFlightPlan client;
	
	public TowerServiceFlightPlan() {}
	
	public FlightPlan getFlightPlan(UUID aircraftId) {
		return client.getFlightPlan(aircraftId);
	}
	
}
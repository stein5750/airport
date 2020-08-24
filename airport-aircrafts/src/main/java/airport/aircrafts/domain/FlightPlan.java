package airport.aircrafts.domain;

import java.util.UUID;

import org.springframework.data.annotation.Id;

public class FlightPlan {
	
	@Id
	private UUID aircraftId;
	
	private Journey arrival;
	
	private Journey departure;

	
	public UUID getAircraftId() {
		return aircraftId;
	}


	public void setAircraftId(UUID aircraftId) {
		this.aircraftId = aircraftId;
	}
	
	
	public FlightPlan () {
		arrival = new Journey();
		departure  = new Journey();
	}

	
	public Journey getArrival() {
		return arrival;
	}


	public void setArrival( Journey arrival) {
		this.arrival = arrival;
	}


	public Journey getDeparture() {
		return departure;
	}


	public void setDeparture( Journey departure) {
		this.departure = departure;
	}
	
}
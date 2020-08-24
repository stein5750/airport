package airport.towerserver.domain;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.data.annotation.Id;

public class Arrival {

	@Id
	private UUID aircraftId;
	
	private String flight;
	
	private String origin;
	
	private LocalDateTime time;

	public UUID getAircraftId() {
		return aircraftId;
	}

	public void setAircraftId(UUID aircraftId) {
		this.aircraftId = aircraftId;
	}

	public String getFlight() {
		return flight;
	}

	public void setFlight(String flight) {
		this.flight = flight;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public LocalDateTime getTime() {
		return time;
	}

	public void setTime(LocalDateTime time) {
		this.time = time;
	} 	

	
}
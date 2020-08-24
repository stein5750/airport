package airport.tower.gateclient.domain;

import org.springframework.data.annotation.Id;

import airport.tower.aircraft.domain.Journey;

public class JourneyAndGateName extends Journey {
	
	@Id
	private String gateName;
	
	public String getGateName() {
		return gateName;
	}

	public void setGateName(String gateName) {
		this.gateName = gateName;
	}
	
}
package airport.monitor.server.domain;

import org.springframework.data.annotation.Id;

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
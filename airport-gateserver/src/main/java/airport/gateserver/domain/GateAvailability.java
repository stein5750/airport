package airport.gateserver.domain;

import org.springframework.data.annotation.Id;

public class GateAvailability {

	@Id 	
	private String gateName;
	
	private Boolean available;

	public String getGateName() {
		return gateName;
	}

	public void setGateName(String gateName) {
		this.gateName = gateName;
	}

	public Boolean getAvailable() {
		return available;
	}

	public void setAvailable(Boolean available) {
		this.available = available;
	}
	
}

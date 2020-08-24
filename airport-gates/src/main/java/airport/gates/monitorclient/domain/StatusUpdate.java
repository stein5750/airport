package airport.gates.monitorclient.domain;

import org.springframework.stereotype.Component;

@Component
public class StatusUpdate {

	private String gateName;
	
	private String status;

	public String getGateName() {
		return gateName;
	}

	public void setGateName(String gateName) {
		this.gateName = gateName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
}

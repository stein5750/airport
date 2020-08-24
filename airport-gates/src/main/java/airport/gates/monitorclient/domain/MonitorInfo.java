package airport.gates.monitorclient.domain;

import org.springframework.stereotype.Component;

import airport.gates.domain.JourneyAndGateName;

@Component
public class MonitorInfo extends JourneyAndGateName{
	
	private String status;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	} 	

}
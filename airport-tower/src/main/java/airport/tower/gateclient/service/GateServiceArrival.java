package airport.tower.gateclient.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import airport.tower.aircraft.domain.Journey;
import airport.tower.gateclient.domain.JourneyAndGateName;
import airport.tower.gateclient.respository.GateClientArrival;

@Service
public class GateServiceArrival {

    @Autowired
    GateClientArrival client;

    public GateServiceArrival() {
    }

    public void saveArrival( String gateName, Journey journey) {
        JourneyAndGateName journeyAndGateName = converter(gateName, journey);
        client.saveArrival(journeyAndGateName);
    }

    public void removeGateInformation( String gateName) {
        client.removeGateInformation(gateName);
    }

    private JourneyAndGateName converter( String gateName, Journey journey) {
        JourneyAndGateName journeyAndGateName = new JourneyAndGateName();
        journeyAndGateName.setGateName(gateName);
        journeyAndGateName.setFlight(journey.getFlight());
        journeyAndGateName.setLocation(journey.getLocation());
        journeyAndGateName.setTime(journey.getTime());
        return journeyAndGateName;
    }

}
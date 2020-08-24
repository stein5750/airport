package airport.tower.gateclient.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import airport.tower.aircraft.domain.Journey;
import airport.tower.gateclient.domain.JourneyAndGateName;
import airport.tower.gateclient.respository.GateClientDeparture;

@Service
public class GateServiceDeparture {

    @Autowired
    GateClientDeparture client;

    public GateServiceDeparture() {
    }

    public void saveDeparture( String gateName, Journey journey) {
        JourneyAndGateName journeyAndGateName = converter(gateName, journey);
        client.saveDeparture(journeyAndGateName);
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
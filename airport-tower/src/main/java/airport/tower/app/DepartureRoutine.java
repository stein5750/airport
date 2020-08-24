package airport.tower.app;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import airport.tower.aircraft.domain.Journey;
import airport.tower.aircraft.domain.Message;
import airport.tower.gateclient.service.GateServiceArrival;
import airport.tower.gateclient.service.GateServiceAvailableGate;
import airport.tower.gateclient.service.GateServiceDeparture;
import airport.tower.towerclient.service.TowerServiceTakeoffPermission;
import airport.tower.towerclient.service.TowerServiceTakeoffRequest;

@Service
public class DepartureRoutine {

    @Autowired
    TowerServiceTakeoffRequest towerServiceTakeoffRequest;

    @Autowired
    TowerServiceTakeoffPermission towerServiceTakeoffPermission;

    @Autowired
    GateServiceAvailableGate gateServiceAvailableGate;

    @Autowired
    GateServiceArrival gateServiceArrival;

    @Autowired
    GateServiceDeparture gateServiceDeparture;

    public DepartureRoutine() {
    }

    public UUID getAircraftId() {
        Message takeoffRequest = towerServiceTakeoffRequest.retrieveTakeoffRequest();
        if (takeoffRequest != null) {
            return takeoffRequest.getAircraftId();
        } else {
            return null;
        }
    }

    public void setGateAvailable( String gateName) {
        gateServiceAvailableGate.saveAvailability(gateName, true);
        gateServiceArrival.removeGateInformation(gateName);
        gateServiceDeparture.removeGateInformation(gateName);
    }

    // Give takeoff permission
    public void giveTakeoffPermission( UUID aircraftId) {
        towerServiceTakeoffPermission.saveTakeoffPermission(aircraftId, "Permission granted");
    }

    // Inform gate about plan for departure
    public void informGateAboutDeparture( String gateName, Journey departure) {
        gateServiceDeparture.saveDeparture(gateName, departure);
    }

}
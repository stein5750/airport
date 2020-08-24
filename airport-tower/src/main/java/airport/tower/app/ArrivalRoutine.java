package airport.tower.app;

import java.util.LinkedList;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import airport.tower.aircraft.domain.FlightPlan;
import airport.tower.aircraft.domain.Journey;
import airport.tower.aircraft.domain.Message;
import airport.tower.gateclient.service.GateServiceArrival;
import airport.tower.gateclient.service.GateServiceAvailableGate;
import airport.tower.gateclient.service.GateServiceDeparture;
import airport.tower.towerclient.service.TowerServiceFlightPlan;
import airport.tower.towerclient.service.TowerServiceLandingPermission;
import airport.tower.towerclient.service.TowerServiceLandingRequest;

@Service
public class ArrivalRoutine {

    @Autowired
    TowerServiceFlightPlan towerServiceFlightPlan;

    @Autowired
    TowerServiceLandingRequest towerServiceLandingRequest;

    @Autowired
    TowerServiceLandingPermission towerServiceLandingPermission;

    @Autowired
    GateServiceAvailableGate gateServiceAvailableGate;

    @Autowired
    GateServiceArrival gateServiceArrival;

    @Autowired
    GateServiceDeparture gateServiceDeparture;

    @Autowired
    DepartureRoutine departureRoutine;

    LinkedList<UUID> arrivingAircraftsIdQue = new LinkedList<UUID>();

    ArrivalRoutine() {
    }

    public UUID getAircraftId() {
        Message landingRequest = towerServiceLandingRequest.getLandingRequest();
        if (landingRequest != null) {
            arrivingAircraftsIdQue.add(landingRequest.getAircraftId());
        }
        return arrivingAircraftsIdQue.peekFirst();
    }

    public void removeAircraftFromQue( UUID id) {
        arrivingAircraftsIdQue.remove(id);
    }

    public String getAvailableGate() {
        return gateServiceAvailableGate.getAvailableGate();
    }

    public void setGateUnavailable( String gateName) {
        gateServiceAvailableGate.saveAvailability(gateName, false);
    }

    // Give landingpermission
    public void giveLandingPermission( UUID aircraftId, String gateName) {
        towerServiceLandingPermission.saveLandingPermission(aircraftId, gateName);
    }

    // get the arriving aircrafts flightplan
    public FlightPlan getFlightPlan( UUID aircraftId) {
        return towerServiceFlightPlan.getFlightPlan(aircraftId);
    }

    // Inform gate about plan for arrival
    public void informGateAboutArrival( String gateName, Journey arrival) {
        gateServiceArrival.saveArrival(gateName, arrival);
    }

}
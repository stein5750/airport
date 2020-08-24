package airport.aircrafts.towerclient.service;

import java.util.UUID;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import airport.aircrafts.domain.FlightPlan;
import airport.aircrafts.domain.Message;
import airport.aircrafts.towerclient.repository.AircraftClient;

@Service
@Scope("prototype")
public class AircraftService {

    private AircraftClient client = new AircraftClient();

    public void sendFlightPlan( FlightPlan flightPlan) {
        client.saveFlightPlan(flightPlan);
        return;
    }

    public void requestLanding( UUID aircraftId, String text) {
        Message message = new Message();
        message.setAircraftId(aircraftId);
        message.setText(text);
        client.requestLanding(message);
        return;
    }

    public Message getLandingPermission( UUID aircraftId) {
        Message message = client.getLandingPermission(aircraftId);
        return message;
    }

    public void requestTakeoff( UUID aircraftId, String text) {
        Message message = new Message();
        message.setAircraftId(aircraftId);
        message.setText(text);
        client.requestTakeoff(message);
        return;
    }

    public Message getTakeoffPermission( UUID aircraftId) {
        return client.getTakeoffPermission(aircraftId);
    }
}

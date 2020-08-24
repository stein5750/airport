package airport.towerserver.domain;

import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Component;

@Component
public class Message {

    @Id
    private UUID aircraftId;

    private String text;

    public UUID getAircraftId() {
        return aircraftId;
    }

    public void setAircraftId( UUID aircraftId) {
        this.aircraftId = aircraftId;
    }

    public String getText() {
        return text;
    }

    public void setText( String text) {
        this.text = text;
    }

}
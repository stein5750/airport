package airport.monitor.server.domain;

import java.time.LocalDateTime;

public class Journey {

    private String flight;

    private String location;

    private LocalDateTime time;

    public String getFlight() {
        return flight;
    }

    public void setFlight( String flight) {
        this.flight = flight;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation( String location) {
        this.location = location;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime( LocalDateTime time) {
        this.time = time;
    }

}
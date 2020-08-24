package airport.monitor.server.domain;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

public class MonitorInfo extends JourneyAndGateName {

    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus( String status) {
        this.status = status;
    }

    @Override
    @JsonFormat(pattern = "yyyy-MM-dd@HH:mm:ss")
    public LocalDateTime getTime() {
        return super.getTime();
    }
}
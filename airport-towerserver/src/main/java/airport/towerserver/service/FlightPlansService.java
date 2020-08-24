package airport.towerserver.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import airport.towerserver.domain.FlightPlan;
import airport.towerserver.repository.FlightPlansRepository;
import reactor.core.publisher.Mono;

@Service
public class FlightPlansService {

    @Autowired
    private FlightPlansRepository repository;

    public Mono<Void> saveFlightPlan( FlightPlan flightPlan) {
        return repository.saveFlightPlan(flightPlan);
    }

    public Mono<FlightPlan> getFlightPlan( UUID aircraftId) {
        return repository.getFlightPlan(aircraftId);
    }

}
package airport.gateserver.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import airport.gateserver.domain.GateAvailability;
import airport.gateserver.repository.AvailabilityRepository;
import reactor.core.publisher.Mono;

@Service
public class AvailabilityService {

    @Autowired
    private AvailabilityRepository repository;

    public Mono<GateAvailability> getAvailableGate() {
        return repository.getAvailableGate();
    };

    public Mono<List<GateAvailability>> getAvailableGatesList() {
        return repository.getAvailableGatesList();
    }

    public Mono<Void> saveGateAvailability( String gateName, boolean status) {
        GateAvailability gateAvailability = new GateAvailability();
        gateAvailability.setGateName(gateName);
        gateAvailability.setAvailable(status);
        return repository.saveGateAvailability(gateAvailability);
    }

    public void removeGateStatus( String gateName) {
        repository.removeGateStatus(gateName);
    }

}

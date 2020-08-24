package airport.monitor.server.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import airport.monitor.server.domain.MonitorInfo;
import airport.monitor.server.repository.DepartureRepository;
import reactor.core.publisher.Mono;

@Service
public class MonitorDepartureService {

    @Autowired
    private DepartureRepository repository;

    public Mono<Void> save( MonitorInfo monitorInfo) {
        return repository.save(monitorInfo);
    }

    public Mono<MonitorInfo> get( String gateName) {
        return repository.get(gateName);
    }

    public Mono<List<MonitorInfo>> getAll() {
        return repository.getAll();
    }

    public Mono<Void> updateStatus( String gateName, String status) {
        return repository.updateStatus(gateName, status);
    }

}
package airport.gateserver.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import airport.gateserver.domain.GateAvailability;
import reactor.core.publisher.Mono;

@Repository
public class AvailabilityRepository {

    private final String collectionGateStatus = "gateserver_availability";

    @Autowired
    private ReactiveMongoTemplate template;

    public Mono<GateAvailability> getAvailableGate() {
        Query query = new Query();
        query.addCriteria(Criteria.where("available").is(true));
        return template.findOne(query, GateAvailability.class, collectionGateStatus);
    }

    public Mono<List<GateAvailability>> getAvailableGatesList() {
        return template.findAll(GateAvailability.class, collectionGateStatus)
                .sort(( o1, o2) -> o1.getGateName().compareTo(o2.getGateName())).collectList();
    }

    public Mono<Void> saveGateAvailability( GateAvailability gateAvailability) {
        return template.save(gateAvailability, collectionGateStatus).then();

    }

    public void removeGateStatus( String gateName) {
        Query query = new Query();
        query.addCriteria(Criteria.where("gateName").is(gateName));
        template.remove(query, GateAvailability.class, collectionGateStatus).subscribe();
    }

}
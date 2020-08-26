package airport.gateserver.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.mongodb.client.result.DeleteResult;

import airport.gateserver.domain.JourneyAndGateName;
import reactor.core.publisher.Mono;

@Repository
public class ArrivalRepository {

    private final String collectionGateArrivals = "gateserver_arrivals";

    @Autowired
    private ReactiveMongoTemplate template;

    public Mono<Void> save( JourneyAndGateName journeyAndGateName) {
        return template.save(journeyAndGateName, collectionGateArrivals).then();
    }

    public Mono<JourneyAndGateName> get( String gateName) {
        Query query = new Query();
        query.addCriteria(Criteria.where("gateName").is(gateName));
        return template.findAndRemove(query, JourneyAndGateName.class, collectionGateArrivals);
    }

    public Mono<DeleteResult> remove( String gateName) {
        Query query = new Query();
        query.addCriteria(Criteria.where("gateName").is(gateName));
        return template.remove(query, JourneyAndGateName.class, collectionGateArrivals);
    }

}
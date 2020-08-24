package airport.towerserver.repository;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import airport.towerserver.domain.FlightPlan;
import reactor.core.publisher.Mono;

@Repository
public class FlightPlansRepository {

    private final String collectionFlightPlans = "towerserver_flightplans";

    @Autowired
    private ReactiveMongoTemplate template;

    public Mono<Void> saveFlightPlan( FlightPlan flightPlan) {
        return template.save(flightPlan, collectionFlightPlans).then();
    }

    public Mono<FlightPlan> getFlightPlan( UUID aircraftId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("aircraftId").is(aircraftId));
        return template.findOne(query, FlightPlan.class, collectionFlightPlans);
    }

}
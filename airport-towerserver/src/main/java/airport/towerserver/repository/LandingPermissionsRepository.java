package airport.towerserver.repository;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import airport.towerserver.domain.Message;
import reactor.core.publisher.Mono;

@Repository
public class LandingPermissionsRepository {

    private final String collectionLandingPermissions = "towerserver_landingpermissions";

    @Autowired
    private ReactiveMongoTemplate template;

    public Mono<Void> saveLandingPermission( Message message) {
        return template.save(message, collectionLandingPermissions).then();
    }

    public Mono<Message> getLandingPermission( UUID aircraftId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("aircraftId").is(aircraftId));
        return template.findAndRemove(query, Message.class, collectionLandingPermissions);
    }

}

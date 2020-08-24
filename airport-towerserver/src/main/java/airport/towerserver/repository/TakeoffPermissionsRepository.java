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
public class TakeoffPermissionsRepository {

    private final String collectionTakeoffPermissions = "towerserver_takeoffpermissions";

    @Autowired
    private ReactiveMongoTemplate template;

    public Mono<Void> saveTakeoffPermission( Message message) {
        return template.save(message, collectionTakeoffPermissions).then();
    }

    public Mono<Message> getTakeoffPermission( UUID aircraftId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("aircraftId").is(aircraftId));
        return template.findAndRemove(query, Message.class, collectionTakeoffPermissions);
    }

}
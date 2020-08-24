package airport.towerserver.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import airport.towerserver.domain.Message;
import reactor.core.publisher.Mono;

@Repository
public class TakeoffRequestsRepository {

    private final String collectionTakeoffRequests = "towerserver_takeoffrequests";

    @Autowired
    private ReactiveMongoTemplate template;

    public Mono<Void> saveTakeoffRequest( Message message) {
        return template.save(message, collectionTakeoffRequests).then();
    }

    public Mono<Message> getTakeoffRequest() {
        Query query = new Query();
        return template.findAndRemove(query, Message.class, collectionTakeoffRequests);
    }

}
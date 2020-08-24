package airport.towerserver.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import airport.towerserver.domain.Message;
import reactor.core.publisher.Mono;

@Repository
public class LandingRequestsRepository {

    private final String collectionLandingRequests = "towerserver_landingrequests";

    @Autowired
    private ReactiveMongoTemplate template;

    public Mono<Void> saveLandingRequest( Message message) {
        return template.save(message, collectionLandingRequests).then();
    }

    public Mono<Message> getLandingRequest() {
        Query query = new Query();
        return template.findAndRemove(query, Message.class, collectionLandingRequests);
    }

}

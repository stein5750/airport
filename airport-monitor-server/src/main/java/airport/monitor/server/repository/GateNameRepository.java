package airport.monitor.server.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.mongodb.BasicDBObject;

import reactor.core.publisher.Mono;

@Repository
public class GateNameRepository {

    private final String collectionGateNames = "monitorserver_gatenames";

    @Autowired
    private ReactiveMongoTemplate template;

    public Mono<Void> saveGateName( String gateName) {
        BasicDBObject document = new BasicDBObject();
        document.put("gateName", gateName);
        return template.insert(document, collectionGateNames).then();
    }

    public Mono<List<String>> getGateNames() {
        Query query = new Query();
        query.fields().exclude("_id");
        return template.find(query, String.class, collectionGateNames).sort(( o1, o2) -> o1.compareToIgnoreCase(o2))
                .collectList();
    }

    public Mono<Void> deleteGateName( String gateName) {
        Query query = new Query();
        query.addCriteria(Criteria.where("gateName").is(gateName));
        return template.remove(query, String.class, collectionGateNames).then();
    }

}
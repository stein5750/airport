package airport.monitor.server.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import airport.monitor.server.domain.MonitorInfo;
import reactor.core.publisher.Mono;

@Repository
public class ArrivalRepository {

    private final String collectionMonitorArrivals = "monitorserver_arrivals";

    @Autowired
    private ReactiveMongoTemplate template;

    public Mono<Void> save( MonitorInfo monitorInfo) {
        return template.save(monitorInfo, collectionMonitorArrivals).then();
    }

    public Mono<MonitorInfo> get( String gateName) {
        Query query = new Query();
        query.addCriteria(Criteria.where("gateName").is(gateName));
        return template.findOne(query, MonitorInfo.class, collectionMonitorArrivals);
    }

    public Mono<List<MonitorInfo>> getAll() {
        return template.findAll(MonitorInfo.class, collectionMonitorArrivals)
                .sort(( o1, o2) -> o1.getTime().compareTo(o2.getTime())).collectList();
    }

    public Mono<Void> updateStatus( String gateName, String status) {
        Query query = new Query();
        query.addCriteria(Criteria.where("gateName").is(gateName));
        Update update = new Update();
        update.set("status", status);
        return template.updateFirst(query, update, MonitorInfo.class, collectionMonitorArrivals).then();
    }

}
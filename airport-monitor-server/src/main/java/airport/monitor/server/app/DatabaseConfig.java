package airport.monitor.server.app;

import java.net.UnknownHostException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.ReactiveMongoDatabaseFactory;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.SimpleReactiveMongoDatabaseFactory;

import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;

@Configuration
public class DatabaseConfig {

    DatabaseConfig() {
    }

    @Bean
    public MongoClient mongoClient() throws UnknownHostException {
        return MongoClients.create("mongodb://localhost");
    }

    public ReactiveMongoDatabaseFactory mongoDbFactory() throws UnknownHostException {
        return new SimpleReactiveMongoDatabaseFactory(mongoClient(), "airportDb");
    }

    @Bean
    public ReactiveMongoTemplate reactiveMongoTemplate() throws UnknownHostException {
        return new ReactiveMongoTemplate(mongoDbFactory());
    }

}

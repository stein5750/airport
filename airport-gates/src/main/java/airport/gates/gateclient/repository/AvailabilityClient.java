package airport.gates.gateclient.repository;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;


@Repository
public class AvailabilityClient {

	private WebClient webClient;

	public AvailabilityClient() {
		webClient = WebClient.create("http://localhost:8080/airport-gateserver/gates");
	}

	//--TODO convert to post
	public void saveAvailability( String gateName, boolean available ) {
		webClient.get().uri("/save/"+gateName+"/"+available).accept(MediaType.APPLICATION_JSON)
		.retrieve()
		.bodyToMono(Void.class)
		.subscribe();
	}

}
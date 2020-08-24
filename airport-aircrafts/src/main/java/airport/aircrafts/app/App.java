package airport.aircrafts.app;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
	
	public static void main(String[] args) {
		
		AnnotationConfigApplicationContext  context = new AnnotationConfigApplicationContext();
		context.scan("airport.aircrafts");
		context.refresh();
		
		ArrivingAircraftSpawner aircraftSpawner = (ArrivingAircraftSpawner) context.getBean("arrivingAircraftSpawner");
		aircraftSpawner.spawnAircrafts();
		context.close();
	}
}
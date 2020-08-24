package airport.gates.app;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {

    public static void main( String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.scan("airport.gates");
        context.refresh();

        GateCreator gateCreator = (GateCreator) context.getBean("gateCreator");
        gateCreator.createGates();
        context.close();
    }
}
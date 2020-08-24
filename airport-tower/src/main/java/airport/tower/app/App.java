package airport.tower.app;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {

    public static void main( String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.scan("airport.tower");
        context.refresh();

        Tower tower = (Tower) context.getBean("tower");
        tower.directTraffic();
        context.close();
    }
}
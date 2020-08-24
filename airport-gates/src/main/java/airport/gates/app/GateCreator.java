package airport.gates.app;

import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import airport.gates.gateclient.service.AvailabilityService;
import airport.gates.monitorclient.service.MonitorGateNameService;
import airport.gates.util.FilePropertyReader;

@Component
public class GateCreator {

    @Autowired
    AnnotationConfigApplicationContext applicationContext;

    @Autowired
    private AvailabilityService availabilityService;

    @Autowired
    private MonitorGateNameService monitorGateNameService;

    private static Logger log = LoggerFactory.getLogger(GateCreator.class);

    private FilePropertyReader properties = new FilePropertyReader();
    private List<String> gateNames;
    private LinkedList<Thread> threads = new LinkedList<Thread>();

    GateCreator() {
        gateNames = properties.getGateNames();
    }

    public void createGates() {
        // create gates
        Thread gateThread;
        for (String gateName : gateNames) {
            Gate gate = (Gate) applicationContext.getBean("gate");
            gate.setGateName(gateName);
            gateThread = new Thread(gate);
            gateThread.start();
            // Set gate available for monitors
            monitorGateNameService.saveGateName(gateName);
            // set gate avialable for towers traffic control at startup
            availabilityService.saveAvailability(gateName, true);
            threads.add(gateThread);
        }
        // wait for gates to finish
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                log.error(e.getMessage());
            }
        }

    }
}

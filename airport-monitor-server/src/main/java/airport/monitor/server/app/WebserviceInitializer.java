package airport.monitor.server.app;

import org.springframework.web.server.adapter.AbstractReactiveWebInitializer;

public class WebserviceInitializer extends AbstractReactiveWebInitializer {

    WebserviceInitializer() {
    }

    @Override
    protected Class<?>[] getConfigClasses() {
        return new Class[] { WebConfig.class, DatabaseConfig.class };
    }

}
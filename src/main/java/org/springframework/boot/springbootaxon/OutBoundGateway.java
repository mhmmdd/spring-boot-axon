package org.springframework.boot.springbootaxon;

import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventhandling.EventMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class OutBoundGateway {
    private final static Logger logger = LoggerFactory.getLogger(OutBoundGateway.class);

    @EventHandler
    public void on(EventMessage event) {
        logger.info("Event: " + event.getPayload());
    }
}

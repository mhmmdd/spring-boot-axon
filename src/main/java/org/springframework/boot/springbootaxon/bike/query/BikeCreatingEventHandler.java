package org.springframework.boot.springbootaxon.bike.query;


import org.axonframework.eventhandling.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.springbootaxon.bike.domain.events.BikeCreatedEvent;
import org.springframework.stereotype.Component;

@Component
public class BikeCreatingEventHandler {
    private final BikeEntryRepository bikeEntryRepository;
    private final static Logger logger = LoggerFactory.getLogger(BikeCreatingEventHandler.class);

    @Autowired
    public BikeCreatingEventHandler(BikeEntryRepository bikeEntryRepository) {
        this.bikeEntryRepository = bikeEntryRepository;
    }

    @EventHandler
    void on(BikeCreatedEvent event) throws InterruptedException {
        logger.info("EventHandler BikeCreatedEvent start");
        Thread.sleep(6000);
//        BikeEntry bikeEntry = new BikeEntry(event.getId(), event.getTitle());
//        bikeEntryRepository.save(bikeEntry);
        logger.info("EventHandler BikeCreatedEvent end");
    }

}

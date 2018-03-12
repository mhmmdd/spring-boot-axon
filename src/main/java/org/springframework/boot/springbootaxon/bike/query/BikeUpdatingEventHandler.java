package org.springframework.boot.springbootaxon.bike.query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BikeUpdatingEventHandler {
    private final BikeEntryRepository bikeEntryRepository;
    private final static Logger logger = LoggerFactory.getLogger(BikeUpdatingEventHandler.class);

    @Autowired
    public BikeUpdatingEventHandler(BikeEntryRepository bikeEntryRepository) {
        this.bikeEntryRepository = bikeEntryRepository;
    }

//    @EventHandler
//    void on(BikeUpdatedEvent event) {
//        logger.info("BikeUpdatedEvent");
////        Optional<BikeEntry> bikeEntry = bikeEntryRepository.findById(event.getId());
////        bikeEntryRepository.save(bikeEntry.get());
//    }
}

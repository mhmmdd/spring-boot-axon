package org.springframework.boot.springbootaxon.bike;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.commandhandling.model.Repository;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.spring.stereotype.Aggregate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.springbootaxon.bike.domain.commands.CreateBikeCommand;
import org.springframework.boot.springbootaxon.bike.domain.commands.ModifyTitleBikeCommand;
import org.springframework.boot.springbootaxon.bike.domain.events.BikeCreatedEvent;
import org.springframework.boot.springbootaxon.bike.domain.events.BikeUpdatedEvent;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import static org.axonframework.commandhandling.model.AggregateLifecycle.apply;

@Entity
@Aggregate
public class Bike {
    private static final long serialVersionUID = -5977984483620451665L;
    private final static Logger logger = LoggerFactory.getLogger(Bike.class);


    @AggregateIdentifier
    private String id;
    private String title;

    @Id
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    private Repository<Bike> repository;


    private boolean rented;

    @NotNull
    private boolean completed;

    Bike() {
    }

    @CommandHandler
    public Bike(CreateBikeCommand command) throws InterruptedException {
        logger.info("CommandHandler start");
//        Thread.sleep(4000);
        apply(new BikeCreatedEvent(command.getId(), command.getTitle()));
        logger.info("CommandHandler end");
    }

    @CommandHandler
    void on(ModifyTitleBikeCommand command) throws InterruptedException {
        logger.info("CommandHandler start");
//        Thread.sleep(4000);
        apply(new BikeUpdatedEvent(command.getId(), command.getTitle()));
        logger.info("CommandHandler end");
    }

    @EventSourcingHandler
    void on(BikeCreatedEvent event) {
        logger.info("EventSourcingHandler BikeCreatedEvent start");
//        Assert.state(false, "Where did you get that bike?!");
        this.id = event.getId();
        this.title = event.getTitle();

        logger.info("EventSourcingHandler BikeCreatedEvent end");
    }

    @EventSourcingHandler
    void on(BikeUpdatedEvent event) {
        logger.info("EventSourcingHandler BikeUpdatedEvent start");
//        Assert.state(false, "Where did you get that bike?!");
        this.id = event.getId();
        this.title = event.getTitle();

        logger.info("EventSourcingHandler BikeUpdatedEvent end");
    }

}

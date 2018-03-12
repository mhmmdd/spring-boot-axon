package org.springframework.boot.springbootaxon.bike.domain.commands;

import org.axonframework.commandhandling.TargetAggregateIdentifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.constraints.NotNull;

public class CreateBikeCommand {

    @NotNull
    @TargetAggregateIdentifier
    private final String id;

    @NotNull
    private final String title;

    private final static Logger logger = LoggerFactory.getLogger(CreateBikeCommand.class);

    public CreateBikeCommand(String id, String title) {
        this.id = id;
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }
}


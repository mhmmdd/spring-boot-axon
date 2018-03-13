package org.springframework.boot.springbootaxon;

import org.axonframework.eventsourcing.DomainEventMessage;
import org.axonframework.eventsourcing.eventstore.AbstractSequencedDomainEventEntry;
import org.axonframework.serialization.Serializer;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

@Entity
@Table(indexes = @Index(columnList = "aggregateIdentifier,sequenceNumber,type", unique = true))
public class CustomDomainEventEntry extends AbstractSequencedDomainEventEntry<String> {
    public CustomDomainEventEntry(DomainEventMessage event, Serializer serializer) {
        super(event, serializer, String.class);
    }

    protected CustomDomainEventEntry() {
    }
}
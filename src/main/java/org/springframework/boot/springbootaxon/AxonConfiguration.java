package org.springframework.boot.springbootaxon;

import org.axonframework.common.jpa.EntityManagerProvider;
import org.axonframework.common.transaction.TransactionManager;
import org.axonframework.eventhandling.EventMessage;
import org.axonframework.eventsourcing.DomainEventMessage;
import org.axonframework.eventsourcing.eventstore.EventData;
import org.axonframework.eventsourcing.eventstore.EventStorageEngine;
import org.axonframework.eventsourcing.eventstore.jpa.JpaEventStorageEngine;
import org.axonframework.serialization.Serializer;
import org.axonframework.serialization.json.JacksonSerializer;
import org.axonframework.serialization.upcasting.event.EventUpcasterChain;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
//@Profile("default")
public class AxonConfiguration {

//    @Bean
//    public JpaEventStorageEngine eventStorageEngine(Serializer serializer, DataSource dataSource,
//                                                    SingleEventUpcaster myUpcaster, EntityManagerProvider entityManagerProvider,
//                                                    TransactionManager transactionManager) throws SQLException {
//        return new JpaEventStorageEngine(serializer, myUpcaster::upcast, dataSource, entityManagerProvider,
//                transactionManager);
//    }

//    @Bean
//    public EventBus eventBus() {
//        return new SimpleEventBus();
//    }

//    @Bean
//    public EventStore eventStore() {
//        return new JpaEventStore(entityManagerProvider());
//    }
    @Bean
    public Serializer axonJsonSerializer() {
        return new JacksonSerializer();
    }

//    @Bean
//    public EventStorageEngine eventStorageEngine(EntityManagerProvider entityManagerProvider,
//                                                 SpringTransactionManager transactionManager) {
//        return new JpaEventStorageEngine(entityManagerProvider, transactionManager);
//    }

    @Bean
    public EventStorageEngine eventStorageEngine(Serializer serializer,
                                                    DataSource dataSource,
                                                    EventUpcasterChain myUpcaster,
                                                    EntityManagerProvider entityManagerProvider,
                                                    TransactionManager transactionManager) throws SQLException {
        JpaEventStorageEngine eventStorageEngine = new JpaEventStorageEngine(serializer,
                myUpcaster,
                dataSource,
                entityManagerProvider,
                transactionManager) {
            @Override
            protected EventData<?> createEventEntity(EventMessage<?> eventMessage, Serializer serializer) {
                return new CustomDomainEventEntry((DomainEventMessage<?>) eventMessage, serializer);
            }

            @Override
            protected String domainEventEntryEntityName() {
                return CustomDomainEventEntry.class.getSimpleName();
            }
        };
        return eventStorageEngine;
    }

    @Bean
    public EventUpcasterChain eventUpcasters(){
        return new EventUpcasterChain();
    }

//    @Bean(name = "accountRepository")
//    public Repository<Bike> accountRepository(final EventBus eventBus) {
//        return new GenericJpaRepository<>(axonEntityManagerProvider(), Bike.class, eventBus);
//    }
//
//    @Bean
//    public EntityManagerProvider axonEntityManagerProvider() {
//        return new ContainerManagedEntityManagerProvider();
//    }

//    @Bean()
//    public Repository<Bike> userAggregateRepository(EventStore eventStore){
//        return new EventSourcingRepository<Bike>(Bike.class, eventStore);
//    }
}
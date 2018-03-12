package org.springframework.boot.springbootaxon;

import org.axonframework.common.jpa.EntityManagerProvider;
import org.axonframework.eventhandling.EventBus;
import org.axonframework.eventsourcing.EventSourcingRepository;
import org.axonframework.eventsourcing.eventstore.EventStorageEngine;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.eventsourcing.eventstore.jpa.JpaEventStorageEngine;
import org.axonframework.spring.messaging.unitofwork.SpringTransactionManager;
import org.springframework.boot.springbootaxon.bike.Bike;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
    public EventStorageEngine eventStorageEngine(EntityManagerProvider entityManagerProvider,
                                                 SpringTransactionManager transactionManager) {
        return new JpaEventStorageEngine(entityManagerProvider, transactionManager);
    }

    @Bean
    public EventSourcingRepository<Bike> accountRepository(EventStore eventStore, EventBus eventBus) {
        EventSourcingRepository<Bike> repository = new EventSourcingRepository<Bike>(
                Bike.class,
                eventStore);
        return repository;
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
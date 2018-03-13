package org.springframework.boot.springbootaxon;

import org.axonframework.common.jpa.EntityManagerProvider;
import org.axonframework.common.transaction.TransactionManager;
import org.axonframework.eventhandling.EventBus;
import org.axonframework.eventsourcing.EventSourcingRepository;
import org.axonframework.eventsourcing.eventstore.EventStorageEngine;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.eventsourcing.eventstore.jdbc.HsqlEventTableFactory;
import org.axonframework.eventsourcing.eventstore.jdbc.JdbcEventStorageEngine;
import org.axonframework.eventsourcing.eventstore.jdbc.PostgresEventTableFactory;
import org.axonframework.eventsourcing.eventstore.jpa.JpaEventStorageEngine;
import org.axonframework.serialization.Serializer;
import org.axonframework.serialization.json.JacksonSerializer;
import org.axonframework.serialization.upcasting.event.EventUpcasterChain;
import org.axonframework.spring.eventhandling.scheduling.java.SimpleEventSchedulerFactoryBean;
import org.axonframework.spring.jdbc.SpringDataSourceConnectionProvider;
import org.axonframework.spring.messaging.unitofwork.SpringTransactionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.springbootaxon.bike.Bike;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

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
    Serializer axonJsonSerializer() {
        return new JacksonSerializer();
    }

//    @Bean
//    public EventStorageEngine eventStorageEngine(EntityManagerProvider entityManagerProvider,
//                                                 SpringTransactionManager transactionManager) {
//        return new JpaEventStorageEngine(entityManagerProvider, transactionManager);
//    }

    @Bean
    public JpaEventStorageEngine eventStorageEngine(Serializer serializer,
                                                    DataSource dataSource,
                                                    EventUpcasterChain myUpcaster,
                                                    EntityManagerProvider entityManagerProvider,
                                                    TransactionManager transactionManager) throws SQLException {
        JpaEventStorageEngine eventStorageEngine = new JpaEventStorageEngine(serializer,
                myUpcaster,
                dataSource,
                entityManagerProvider,
                transactionManager);
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
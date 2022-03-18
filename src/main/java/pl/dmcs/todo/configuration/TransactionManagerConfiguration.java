package pl.dmcs.todo.configuration;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.data.transaction.ChainedTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class TransactionManagerConfiguration {

    @Bean(name = "mongoTransactionManager")
    public MongoTransactionManager mongoTransactionManager(MongoDatabaseFactory factory) {
        return new MongoTransactionManager(factory);
    }

    @Bean
    @Primary
    public ChainedTransactionManager chainedTransactionManager(@Qualifier("primaryTransactionManager") PlatformTransactionManager primaryTransactionManager,
                                                               @Qualifier("analyticsTransactionManager") PlatformTransactionManager analyticsTransactionManager,
                                                               @Qualifier("mongoTransactionManager") MongoTransactionManager mongoTransactionManager) {
        return new ChainedTransactionManager(primaryTransactionManager, analyticsTransactionManager, mongoTransactionManager);
    }
}

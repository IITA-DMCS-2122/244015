package pl.dmcs.todo.configuration;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Map;

@Configuration
@EnableJpaAuditing
@EnableJpaRepositories(basePackages = "pl.dmcs.todo.repository.analytics",
        entityManagerFactoryRef = "analyticsEntityManagerFactory",
        transactionManagerRef = "analyticsTransactionManager")
public class AnalyticsDataSourceConfiguration {

    @Bean(name = "analyticsDataSource")
    @ConfigurationProperties(prefix = "datasource.analytics")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "analyticsEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(dataSource())
                .packages("pl.dmcs.todo.entity.analytics")
                .properties(Map.of(
                        "hibernate.hbm2ddl.auto", "update",
                        "hibernate.physical_naming_strategy", "org.hibernate.boot.model.naming.CamelCaseToUnderscoresNamingStrategy"
                ))
                .build();
    }

    @Bean(name = "analyticsTransactionManager")
    public PlatformTransactionManager transactionManager(@Qualifier("analyticsEntityManagerFactory") EntityManagerFactory factory) {
        return new JpaTransactionManager(factory);
    }
}

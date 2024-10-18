package single.project.stakeapi.configuration.datasource;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import single.project.stakeapi.application.StakeApi;
import single.project.stakeapi.configuration.database.BaseDataSourceBuilder;
import single.project.stakeapi.configuration.database.hibernate.HibernateFactoryBeanBuilder;
import single.project.stakeapi.configuration.database.hibernate.HibernateProperties;

import javax.sql.DataSource;

@Slf4j
@Configuration
@EnableJpaRepositories(
        basePackageClasses = {StakeApi.class},
        entityManagerFactoryRef = "entityManagerFactoryStakeApi",
        transactionManagerRef = "transactionManagerStakeApi",
        includeFilters = @ComponentScan.Filter(classes = StakeApiDataSource.class)
)
public class StakeApiDataSourceConfiguration {
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public HibernateProperties datasourcePropertiesStakeApi() {
        return new HibernateProperties();
    }

    @Bean
    public DataSource dataSourceStakeApi() {
        return new BaseDataSourceBuilder(datasourcePropertiesStakeApi()).build();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryStakeApi() {
        return new HibernateFactoryBeanBuilder(datasourcePropertiesStakeApi())
                .setDataSource(dataSourceStakeApi())
                .build();
    }

    @Bean
    public PlatformTransactionManager transactionManagerStakeApi() {
        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
        jpaTransactionManager.setEntityManagerFactory(entityManagerFactoryStakeApi().getObject());

        return jpaTransactionManager;
    }
}

package io.dimasan.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.persistenceunit.PersistenceUnitManager;
import org.springframework.orm.jpa.vendor.AbstractJpaVendorAdapter;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class HibernateJpaConfig {

    @Autowired
    private DataSource dataSource;

    @Autowired(required = false)
    private PersistenceUnitManager persistenceUnitManager;

    private Map<String, String> properties = new HashMap<>();

    public HibernateJpaConfig() {
        properties.put("hibernate.hbm2ddl.auto", "create-drop");
//        properties.put("hibernate.show_sql", "true");
        properties.put("hibernate.implicit_naming_strategy", "org.hibernate.boot.model.naming.ImplicitNamingStrategyComponentPathImpl");
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        return new JpaTransactionManager();
    }

    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        AbstractJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setShowSql(true);
        vendorAdapter.setDatabase(Database.H2);
        vendorAdapter.setDatabasePlatform("H2");
        vendorAdapter.setGenerateDdl(true);
        return vendorAdapter;
    }

    @Bean
    public EntityManagerFactoryBuilder entityManagerFactoryBuilder(JpaVendorAdapter jpaVendorAdapter) {
        EntityManagerFactoryBuilder builder = new EntityManagerFactoryBuilder(
                jpaVendorAdapter, properties, this.persistenceUnitManager
        );
        builder.setCallback(null);
        return builder;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(EntityManagerFactoryBuilder builder) {
        Map<String, Object> vendorProperties = new LinkedHashMap<>();
        vendorProperties.putAll(properties);

        return builder.dataSource(this.dataSource).packages("io.dimasan.domain")
                .properties(vendorProperties).jta(false).build();
    }
}

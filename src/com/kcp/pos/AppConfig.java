/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kcp.pos;

import java.util.HashMap;
import java.util.Map;
import javax.activation.DataSource;
import javax.annotation.Resource;
import javax.persistence.EntityManagerFactory;
import org.hibernate.cfg.Environment;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 *
 * @author kcpavan
 */
@Configuration
@ComponentScan(basePackages = "base.package", excludeFilters=@Filter(Configuration.class)) // Find our @Component-classes; the excludeFilters is no longer necessary with 3.1 RC2
@EnableTransactionManagement // Transaction management
@PropertySource("classpath:persistence.properties") // External properties: in development our environment decides we want to log more.


        class ApplicationConfig {
 
    @Autowired
    private DataSource dataSource; // From one of our nested classes
    @Autowired
    private String hibernateDialect; // From one of our nested classes
    @Autowired
    private Environment environment;
 
 
    @Bean
    public FactoryBean<EntityManagerFactory> entityManagerFactory() {
 
        Map<String, Object> jpaProperties = new HashMap<String, Object>();
        jpaProperties.put("hibernate.dialect", hibernateDialect);
        jpaProperties.put("hibernate.hbm2ddl.auto", environment.getProperty("hibernate.hbm2ddl.auto"));
        jpaProperties.put("hibernate.show_sql", environment.getProperty("hibernate.show_sql"));
        jpaProperties.put("hibernate.format_sql", environment.getProperty("hibernate.format_sql"));
        jpaProperties.put("hibernate.use_sql_comments", environment.getProperty("hibernate.use_sql_comments"));
 
        LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        localContainerEntityManagerFactoryBean.setJpaPropertyMap(jpaProperties);
        localContainerEntityManagerFactoryBean.setDataSource(dataSource);
 
        return localContainerEntityManagerFactoryBean;
    }
 
 
    @Bean(name = AbstractApplicationContext.MESSAGE_SOURCE_BEAN_NAME) // Nice: no hardcoded name.
    public MessageSource messageSource() {
 
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("messages"); // Our i18n translations are in messages.properties, messages_nl.properties, ...
        return messageSource;
    }
 
 
    @Bean
    // We need to define a transaction manager ourselves. For production use we could name it anything we want (unlike when
    // using XML), but the TransactionalTestExecutionListener in spring-test requires us to either specify this name, or use
    // the default name ("transactionManager").
    public PlatformTransactionManager transactionManager() {
 
        EntityManagerFactory factory;
        try {
            // Needed because our method returns a factory; not the object itself.
            factory = entityManagerFactory().getObject();
        } catch (Exception e) {
            throw new IllegalStateException("Failed to create an EntityManagerFactory", e);
        }
        return new JpaTransactionManager(factory);
    }
 
 
    /**
     * Beans for production use. This is a nested class, and as such is automatically imported.
     */
    @Configuration
    @Profile("production") // For normal use.
    public static class JndiConnections {
 
        @Resource(mappedName = "jdbc/OurAppDataSource")
        private DataSource dataSource;
        @Resource(mappedName = "OurAppHibernateDialect")
        private String hibernateDialect;
 
 
        @Bean
        public DataSource dataSource() {
 
            return dataSource;
        }
 
 
        @Bean
        public String hibernateDialect() {
 
            return hibernateDialect;
        }
    }
 
    /**
     * Beans for testing.
     */
    @Configuration
    @Profile("testing") // Only unit tests.
    public static class LocalConnections {
 
        @Bean
        public DataSource dataSource() {
 
            DriverManagerDataSource delegate = new DriverManagerDataSource();
            delegate.setDriverClassName("org.hsqldb.jdbcDriver"); // As String, as the dependency is test (not compile).
            delegate.setUrl("jdbc:hsqldb:mem:postduif;sql.enforce_strict_size=true");
            delegate.setUsername("sa");
            delegate.setPassword("");
 
            return dataSource;
        }
 
 
        @Bean
        public String hibernateDialect() {
 
            // As String, as the dependency is runtime.
            return "org.hibernate.dialect.HSQLDialect";
        }
    }
}
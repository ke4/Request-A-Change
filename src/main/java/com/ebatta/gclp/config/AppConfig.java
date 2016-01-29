package com.ebatta.gclp.config;

import javax.servlet.ServletContext;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

@Configuration
@ComponentScan(basePackages = { "com.ebatta.gclp" })
public class AppConfig {

    private static final Logger logger = LoggerFactory.getLogger(AppConfig.class);

    @Autowired
    ServletContext servletContext;

    private String env = System.getProperty("spring.profiles.active");

    @Bean
    public DataSourceInitializer dataSourceInitializer(final DataSource dataSource) {
        logger.debug("dataSourceInitializer - start");

        final DataSourceInitializer initializer = new DataSourceInitializer();
        initializer.setDataSource(dataSource);
        initializer.setDatabasePopulator(databasePopulator());

        logger.debug("dataSourceInitializer - end");

        return initializer;
    }

    private DatabasePopulator databasePopulator() {
        logger.debug("databasePopulator - start");

        final ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.addScript(new ClassPathResource("dbscripts/" + env + "_db-schema.sql"));
        populator.addScript(new ClassPathResource("dbscripts/" + env + "_db-seed.sql"));

        return populator;
    }
}

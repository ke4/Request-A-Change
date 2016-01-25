package com.ebatta.gclp.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.web.context.support.ServletContextResource;

@Configuration
@ComponentScan(basePackages = { "com.ebatta.gclp" })
public class AppConfig {

    private static final Logger logger = LoggerFactory.getLogger(AppConfig.class);

    @Autowired
    ServletContext servletContext;

    @Bean
    public DataSource dataSource() throws IOException {
        logger.debug("datasource :: init");

        Properties dbProp = new Properties();
        getDBProperties(dbProp);

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(dbProp.getProperty("jdbc.driver"));
        dataSource.setUrl(dbProp.getProperty("jdbc.url"));
        dataSource.setUsername(dbProp.getProperty("jdbc.username"));
        dataSource.setPassword(dbProp.getProperty("jdbc.password"));

        logger.debug("datasource :: ends");

        return dataSource;
    }

    private Properties getDBProperties(Properties dbProp) throws IOException {
        try ( InputStream inStream = new FileInputStream(
                new ServletContextResource(servletContext,"/WEB-INF/classes/config/dbConnection.properties").getFile())
        ) {
            dbProp.load(inStream);
        }

        return dbProp;
    }

    @Value("/WEB-INF/classes/dbscripts/db-schema.sql")
    private Resource schemaScript;
    
    @Value("/WEB-INF/classes/dbscripts/db-seed.sql")
    private Resource seedScript;

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
        populator.addScript(schemaScript);
        populator.addScript(seedScript);

        return populator;
    }
}

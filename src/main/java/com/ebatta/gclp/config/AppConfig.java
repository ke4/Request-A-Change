package com.ebatta.gclp.config;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

@Configuration
@ComponentScan(basePackages = { "com.ebatta.gclp.service" })
public class AppConfig {

    @Autowired
    ServletContext servletContext;

    @Bean
    public ReloadableResourceBundleMessageSource messageSource() {
        ReloadableResourceBundleMessageSource rb = new ReloadableResourceBundleMessageSource();
        rb.setBasenames(new String[] { "classpath:i18n/messages", "classpath:messages/validation" });
        return rb;
    }
}

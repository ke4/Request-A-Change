package com.ebatta.gclp.config;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;

import com.ebatta.gclp.service.ChangeRequestService;

public class TestContext {

    @Bean
    public ChangeRequestService changeRequestService() {
        return Mockito.mock(ChangeRequestService.class);
    }
}

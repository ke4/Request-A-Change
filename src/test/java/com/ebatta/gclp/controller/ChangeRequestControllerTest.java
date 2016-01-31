package com.ebatta.gclp.controller;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.ebatta.gclp.config.PersistenceJPAConfig;
import com.ebatta.gclp.config.TestContext;
import com.ebatta.gclp.config.WebConfig;
import com.ebatta.gclp.exception.ChangeRequestNotFoundException;
import com.ebatta.gclp.persistence.model.ChangeRequest;
import com.ebatta.gclp.persistence.model.ChangeRequestBuilder;
import com.ebatta.gclp.persistence.model.RequestStateEnum;
import com.ebatta.gclp.persistence.model.RiskEnum;
import com.ebatta.gclp.service.ChangeRequestService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebConfig.class, PersistenceJPAConfig.class, TestContext.class})
@WebAppConfiguration
public class ChangeRequestControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private ChangeRequestService changeRequestServiceMock;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setUp() {
        //We have to reset our mock between tests because the mock objects
        //are managed by the Spring container. If we would not reset them,
        //stubbing and verified behavior would "leak" from one test to another.
        Mockito.reset(changeRequestServiceMock);

        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void findAllChangeRequests_ShouldReturnAllChangeRequests() throws Exception {
        ChangeRequest cr1 = new ChangeRequestBuilder()
                .id(1)
                .title("New CR title 1")
                .summary("New CR summary 1")
                .detail("New CR detail 1")
                .control("New CR control 1")
                .customer("New customer 1")
                .risk(RiskEnum.High)
                .state(RequestStateEnum.Submitted)
                .build();
        ChangeRequest cr2 = new ChangeRequestBuilder()
                .id(2)
                .title("New CR title 2")
                .summary("New CR summary 2")
                .detail("New CR detail 2")
                .control("New CR control 2")
                .customer("New customer 2")
                .risk(RiskEnum.Medium)
                .state(RequestStateEnum.Approved)
                .build();

        when(changeRequestServiceMock.findAll()).thenReturn(Arrays.asList(cr1, cr2));

        mockMvc.perform(get("/changerequests"))
                .andExpect(status().isOk())
                .andExpect(view().name("changerequest/list"))
                .andExpect(forwardedUrl("/WEB-INF/views/changerequest/list.jsp"))
                .andExpect(model().attribute("changeRequests", hasSize(2)))
                .andExpect(model().attribute("changeRequests", hasItem(
                        allOf(
                                hasProperty("id", is(1)),
                                hasProperty("title", is("New CR title 1")),
                                hasProperty("summary", is("New CR summary 1"))
                        )
                )))
                .andExpect(model().attribute("changeRequests", hasItem(
                        allOf(
                                hasProperty("id", is(2)),
                                hasProperty("title", is("New CR title 2")),
                                hasProperty("summary", is("New CR summary 2"))
                        )
                )));

        verify(changeRequestServiceMock, times(1)).findAll();
        verifyNoMoreInteractions(changeRequestServiceMock);
    }

    @Test
    public void findById_ChangeRequestEntryNotFound_ShouldRenderErrorView() throws Exception {
        when(changeRequestServiceMock.findById(111))
            .thenThrow(new ChangeRequestNotFoundException("No Change request has been found with the following id: 111"));

        mockMvc.perform(get("/changerequest/{id}", 111))
                .andExpect(status().isNotFound())
                .andExpect(view().name("error/cr_notfound"))
                .andExpect(forwardedUrl("/WEB-INF/views/error/cr_notfound.jsp"));

        verify(changeRequestServiceMock, times(1)).findById(111);
        verifyZeroInteractions(changeRequestServiceMock);
    }
}

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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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

    private static final int TEST_DATA_CR_ID = 1;
    private static final String TEST_DATA_CR_TITLE = "CR title 1";
    private static final String TEST_DATA_CR_SUMMARY = "CR summary 1";
    private static final String TEST_DATA_CR_DETAIL = "CR detail 1";
    private static final String TEST_DATA_CR_CONTROL = "CR control 1";
    private static final String TEST_DATA_CR_CUSTOMER = "customer 1";
    private static final String TEST_DATA_CR_TITLE2 = "CR title 2";
    private static final String TEST_DATA_CR_SUMMARY2 = "CR summary 2";
    private static final String TEST_DATA_CR_DETAIL2 = "CR detail 2";
    private static final String TEST_DATA_CR_CONTROL2 = "CR control 2";
    private static final String TEST_DATA_CR_CUSTOMER2 = "customer 2";
    private static final RiskEnum TEST_DATA_CR_RISK = RiskEnum.High;
    private static final RequestStateEnum TEST_DATA_CR_STATE = RequestStateEnum.Submitted;


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
            .title(TEST_DATA_CR_TITLE)
            .summary(TEST_DATA_CR_SUMMARY)
            .detail(TEST_DATA_CR_DETAIL)
            .control(TEST_DATA_CR_CONTROL)
            .customer(TEST_DATA_CR_CUSTOMER)
            .risk(RiskEnum.High)
            .state(RequestStateEnum.Submitted)
            .build();
        ChangeRequest cr2 = new ChangeRequestBuilder()
            .id(2)
            .title(TEST_DATA_CR_TITLE2)
            .summary(TEST_DATA_CR_SUMMARY2)
            .detail(TEST_DATA_CR_DETAIL2)
            .control(TEST_DATA_CR_CONTROL2)
            .customer(TEST_DATA_CR_CUSTOMER2)
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
                    hasProperty("title", is(TEST_DATA_CR_TITLE)),
                    hasProperty("summary", is(TEST_DATA_CR_SUMMARY)),
                    hasProperty("detail", is(TEST_DATA_CR_DETAIL)),
                    hasProperty("control", is(TEST_DATA_CR_CONTROL)),
                    hasProperty("customer", is(TEST_DATA_CR_CUSTOMER))
                )
            )))
            .andExpect(model().attribute("changeRequests", hasItem(
                allOf(
                    hasProperty("id", is(2)),
                    hasProperty("title", is(TEST_DATA_CR_TITLE2)),
                    hasProperty("summary", is(TEST_DATA_CR_SUMMARY2)),
                    hasProperty("detail", is(TEST_DATA_CR_DETAIL2)),
                    hasProperty("control", is(TEST_DATA_CR_CONTROL2)),
                    hasProperty("customer", is(TEST_DATA_CR_CUSTOMER2))
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

    @Test
    public void findById_ChangeRequestEntryFound_ShouldRenderView() throws Exception {

        ChangeRequest cr = new ChangeRequestBuilder()
            .id(TEST_DATA_CR_ID)
            .title(TEST_DATA_CR_TITLE)
            .summary(TEST_DATA_CR_SUMMARY)
            .detail(TEST_DATA_CR_DETAIL)
            .control(TEST_DATA_CR_CONTROL)
            .customer(TEST_DATA_CR_CUSTOMER)
            .risk(TEST_DATA_CR_RISK)
            .state(TEST_DATA_CR_STATE)
            .build();
        when(changeRequestServiceMock.findById(1)).thenReturn(cr);

        mockMvc.perform(get("/changerequest/{id}", 1))
            .andExpect(status().isOk())
            .andExpect(view().name("changerequest/view"))
            .andExpect(forwardedUrl("/WEB-INF/views/changerequest/view.jsp"))
            .andExpect(model().attribute("changeRequest", hasProperty("id", is(TEST_DATA_CR_ID))))
            .andExpect(model().attribute("changeRequest", hasProperty("title", is(TEST_DATA_CR_TITLE))))
            .andExpect(model().attribute("changeRequest", hasProperty("summary", is(TEST_DATA_CR_SUMMARY))))
            .andExpect(model().attribute("changeRequest", hasProperty("detail", is(TEST_DATA_CR_DETAIL))))
            .andExpect(model().attribute("changeRequest", hasProperty("control", is(TEST_DATA_CR_CONTROL))))
            .andExpect(model().attribute("changeRequest", hasProperty("customer", is(TEST_DATA_CR_CUSTOMER))))
            .andExpect(model().attribute("changeRequest", hasProperty("risk", is(TEST_DATA_CR_RISK))))
            .andExpect(model().attribute("changeRequest", hasProperty("state", is(TEST_DATA_CR_STATE))));

        verify(changeRequestServiceMock, times(1)).findById(1);
        verifyNoMoreInteractions(changeRequestServiceMock);
    }

    @Test
    public void deleteById_ChangeRequestEntryFound_ShouldDeleteChangeRequestAndRenderChangeRequestListView() throws Exception {
        ChangeRequest cr = new ChangeRequestBuilder()
            .id(TEST_DATA_CR_ID)
            .title(TEST_DATA_CR_TITLE)
            .summary(TEST_DATA_CR_SUMMARY)
            .detail(TEST_DATA_CR_DETAIL)
            .control(TEST_DATA_CR_CONTROL)
            .customer(TEST_DATA_CR_CUSTOMER)
            .risk(TEST_DATA_CR_RISK)
            .state(TEST_DATA_CR_STATE)
            .build();
        when(changeRequestServiceMock.deleteById(1)).thenReturn(cr);

        mockMvc.perform(post("/changerequest/{id}/delete", 1))
            .andExpect(status().isFound())
            .andExpect(view().name("redirect:/changerequests"));

        verify(changeRequestServiceMock, times(1)).deleteById(1);
        verifyNoMoreInteractions(changeRequestServiceMock);
    }

    @Test
    public void deleteById_ChangeRequestEntryNotFound_ShouldRenderErrorView() throws Exception {
        when(changeRequestServiceMock.deleteById(111)).thenThrow(
            new ChangeRequestNotFoundException("No Change request has been found with the following id: 111"));

        mockMvc.perform(post("/changerequest/{id}/delete", 111))
            .andExpect(status().isNotFound())
            .andExpect(view().name("error/cr_notfound"))
            .andExpect(forwardedUrl("/WEB-INF/views/error/cr_notfound.jsp"));

        verify(changeRequestServiceMock, times(1)).deleteById(111);
        verifyZeroInteractions(changeRequestServiceMock);
    }
}

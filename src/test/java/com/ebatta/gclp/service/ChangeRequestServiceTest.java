package com.ebatta.gclp.service;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.ebatta.gclp.config.AppConfig;
import com.ebatta.gclp.config.PersistenceJPAConfig;
import com.ebatta.gclp.config.WebConfig;
import com.ebatta.gclp.exception.ChangeRequestNotFoundException;
import com.ebatta.gclp.persistence.model.ChangeRequest;
import com.ebatta.gclp.persistence.model.RequestStateEnum;
import com.ebatta.gclp.persistence.model.RiskEnum;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
//ApplicationContext will be loaded from AppConfig and TestConfig
@ContextConfiguration(classes = {WebConfig.class, PersistenceJPAConfig.class, AppConfig.class})
public class ChangeRequestServiceTest {

    @Autowired
    private ChangeRequestService service;
    
    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void whenFindByIdExistingChangeRequest_ShouldReturnIt() throws ChangeRequestNotFoundException {
        ChangeRequest cr = service.findById(1);
        assertEquals(1, cr.getId());
        assertEquals("test title 1", cr.getTitle());
        assertEquals("test summary 1", cr.getSummary());
        assertEquals("test detail 1", cr.getDetail());
        assertEquals("test control 1", cr.getControl());
        assertEquals("test customer 1", cr.getCustomer());
        assertEquals("Low", cr.getRisk().toString());
        assertEquals("Approved", cr.getState().toString());
    }

    @Test(expected=ChangeRequestNotFoundException.class)
    public void whenFindByIdNotExistingChangeRequest_ShouldThrowException() throws ChangeRequestNotFoundException {
        service.findById(111);
    }

    @Test
    public void WhenFindAllExistingChangeRequest_ShouldReturnAList() {
        List<ChangeRequest> changeRequests = service.findAll();
        assertEquals(5, changeRequests.size());
    }

    @Test
    @Transactional
    @Rollback(true)
    public void whenCreate_ShouldPersistToDatabase() throws ChangeRequestNotFoundException {
        String changeRequestTitle = "New change request";
        ChangeRequest newCR = createNewChangeRequest(changeRequestTitle, "New change request summary",
            "The details of the new change request", "The control statement of the new change request",
            "Customer 1", RiskEnum.High, RequestStateEnum.Submitted);

        ChangeRequest persistedChangeRequest = service.create(newCR);

        assertEquals(changeRequestTitle, persistedChangeRequest.getTitle());
    }

    @Test
    @Transactional
    @Rollback(true)
    public void whenUpdateExistingChangeRequest_ShouldPersistTheUpdatedEntityToDatabase() throws ChangeRequestNotFoundException {
        ChangeRequest crToUpdate = service.findById(3);
        assertEquals(RequestStateEnum.Submitted, crToUpdate.getState());
        crToUpdate.setState(RequestStateEnum.Approved);

        ChangeRequest updatedpersistedChangeRequest = service.update(crToUpdate);

        assertEquals(RequestStateEnum.Approved, updatedpersistedChangeRequest.getState());
    }

    @Test(expected=ChangeRequestNotFoundException.class)
    @Transactional
    @Rollback(true)
    public void whenDeleteChangeRequest_ShouldRemoveItFromDatabase() throws ChangeRequestNotFoundException {
        String changeRequestTitle = "New change request 2";
        ChangeRequest newCR = createNewChangeRequest(changeRequestTitle, "New change request summary 2",
                "The details of the new change request 2", "The control statement of the new change request 2",
                "Customer 2", RiskEnum.High, RequestStateEnum.Submitted);
        service.create(newCR);
        
        ChangeRequest savedChangeRequest = service.findByTitle(changeRequestTitle);
        assertEquals(changeRequestTitle, savedChangeRequest.getTitle());

        service.delete(savedChangeRequest.getId());

        service.findByTitle(changeRequestTitle);
    }

    private ChangeRequest createNewChangeRequest(
            String title, String summary, String detail, String control,
            String customer, RiskEnum risk, RequestStateEnum state) {
        ChangeRequest newCR = new ChangeRequest();
        newCR.setTitle(title);
        newCR.setSummary(summary);
        newCR.setDetail(detail);
        newCR.setControl(control);
        newCR.setCustomer(customer);
        newCR.setRisk(risk);
        newCR.setState(state);

        return newCR;
    }
}

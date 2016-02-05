package com.ebatta.gclp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ebatta.gclp.exception.ChangeRequestNotFoundException;
import com.ebatta.gclp.persistence.dao.ChangeRequestDao;
import com.ebatta.gclp.persistence.dto.ChangeRequestDTO;
import com.ebatta.gclp.persistence.model.ChangeRequest;

@Service
@Transactional
public class ChangeRequestServiceImpl implements ChangeRequestService {

    @Autowired
    private ChangeRequestDao dao;
    
    @Override
    public ChangeRequest findById(Integer id) throws ChangeRequestNotFoundException {
        ChangeRequest changeRequest = dao.findById(id);
        if (changeRequest == null) {
            throw new ChangeRequestNotFoundException(
                    "No Change request has been found with the following id: " + id);
        }

        return changeRequest;
    }

    @Override
    public ChangeRequest findByTitle(final String title) throws ChangeRequestNotFoundException {
        ChangeRequest changeRequest = dao.findByTitle(title);
        if (changeRequest == null) {
            throw new ChangeRequestNotFoundException(
                    "No Change request has been found with the following title: " + title);
        }

        return changeRequest;
    }

    @Override
    public List<ChangeRequest> findAll() {
        return dao.findAll();
    }

    @Override
    public ChangeRequest create(ChangeRequestDTO dto) {
        ChangeRequest newChangeRequest = toEntity(dto);
        return dao.create(newChangeRequest);
    }

    @Override
    public ChangeRequest update(ChangeRequestDTO changeRequest) throws ChangeRequestNotFoundException {
        ChangeRequest changeRequestToUpdate = dao.findById(changeRequest.getId());

        changeRequestToUpdate.setTitle(changeRequest.getTitle());
        changeRequestToUpdate.setSummary(changeRequest.getSummary());
        changeRequestToUpdate.setDetail(changeRequest.getDetail());
        changeRequestToUpdate.setControl(changeRequest.getControl());
        changeRequestToUpdate.setCustomer(changeRequest.getCustomer());
        changeRequestToUpdate.setRisk(changeRequest.getRisk());
        changeRequestToUpdate.setState(changeRequest.getState());

        return dao.update(changeRequestToUpdate);
    }

    @Override
    public ChangeRequest deleteById(Integer id) throws ChangeRequestNotFoundException {
        return dao.deleteById(id);
    }

    private ChangeRequest toEntity(ChangeRequestDTO dto) {
        ChangeRequest changeRequest = ChangeRequest.getBuilder()
            .id(dto.getId())
            .title(dto.getTitle())
            .summary(dto.getSummary())
            .detail(dto.getDetail())
            .control(dto.getControl())
            .customer(dto.getCustomer())
            .risk(dto.getRisk())
            .state(dto.getState())
            .build();

        return changeRequest;
    }
}

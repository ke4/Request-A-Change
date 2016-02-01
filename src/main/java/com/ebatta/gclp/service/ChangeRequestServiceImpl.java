package com.ebatta.gclp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ebatta.gclp.exception.ChangeRequestNotFoundException;
import com.ebatta.gclp.persistence.dao.ChangeRequestDao;
import com.ebatta.gclp.persistence.model.ChangeRequest;

@Service
@Transactional
public class ChangeRequestServiceImpl implements ChangeRequestService {

    @Autowired
    private ChangeRequestDao dao;
    
    @Override
    public ChangeRequest findById(int id) throws ChangeRequestNotFoundException {
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
    public ChangeRequest create(ChangeRequest changeRequest) {
        return dao.create(changeRequest);
    }

    @Override
    public ChangeRequest update(ChangeRequest changeRequest) throws ChangeRequestNotFoundException {
        ChangeRequest changeRequestToUpdate = dao.findById(changeRequest.getId());
        return dao.update(changeRequestToUpdate);
    }

    @Override
    public ChangeRequest deleteById(int id) throws ChangeRequestNotFoundException {
        return dao.deleteById(id);
    }

}

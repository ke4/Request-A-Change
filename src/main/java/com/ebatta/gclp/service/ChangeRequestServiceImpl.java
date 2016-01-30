package com.ebatta.gclp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ebatta.gclp.persistence.dao.ChangeRequestDao;
import com.ebatta.gclp.persistence.model.ChangeRequest;

@Service
@Transactional
public class ChangeRequestServiceImpl implements ChangeRequestService {

    @Autowired
    private ChangeRequestDao dao;
    
    @Override
    public ChangeRequest findById(int id) {
        return dao.findById(id);
    }

    @Override
    public ChangeRequest findByTitle(final String title) {
        return dao.findByTitle(title);
    }

    @Override
    public List<ChangeRequest> findAll() {
        return dao.findAll();
    }

    @Override
    public void create(ChangeRequest changeRequest) {
        dao.create(changeRequest);
    }

    @Override
    public void update(ChangeRequest changeRequest) {
        dao.update(changeRequest);
    }

    @Override
    public void delete(int id) {
       dao.deleteById(id);
    }

}

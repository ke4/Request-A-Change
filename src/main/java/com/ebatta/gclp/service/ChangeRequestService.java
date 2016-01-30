package com.ebatta.gclp.service;

import java.util.List;

import com.ebatta.gclp.persistence.model.ChangeRequest;

public interface ChangeRequestService {

    ChangeRequest findById(int id);

    ChangeRequest findByTitle(final String title);
    
    List<ChangeRequest> findAll();

    void create(ChangeRequest changeRequest);

    void update(ChangeRequest changeRequest);

    void delete(int id);
}

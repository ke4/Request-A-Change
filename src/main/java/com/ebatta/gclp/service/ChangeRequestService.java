package com.ebatta.gclp.service;

import java.util.List;

import com.ebatta.gclp.persistence.model.ChangeRequest;
import com.ebatta.gclp.exception.ChangeRequestNotFoundException;;

public interface ChangeRequestService {

    ChangeRequest findById(int id) throws ChangeRequestNotFoundException;

    ChangeRequest findByTitle(final String title) throws ChangeRequestNotFoundException;
    
    List<ChangeRequest> findAll();

    ChangeRequest create(ChangeRequest changeRequest);

    ChangeRequest update(ChangeRequest changeRequest) throws ChangeRequestNotFoundException;

    ChangeRequest deleteById(int id) throws ChangeRequestNotFoundException;
}

package com.ebatta.gclp.service;

import java.util.List;

import com.ebatta.gclp.exception.ChangeRequestNotFoundException;
import com.ebatta.gclp.persistence.dto.ChangeRequestDTO;
import com.ebatta.gclp.persistence.model.ChangeRequest;;

public interface ChangeRequestService {

    ChangeRequest findById(Integer id) throws ChangeRequestNotFoundException;

    ChangeRequest findByTitle(final String title) throws ChangeRequestNotFoundException;
    
    List<ChangeRequest> findAll();

    ChangeRequest create(ChangeRequestDTO changeRequest);

    ChangeRequest update(ChangeRequestDTO changeRequest) throws ChangeRequestNotFoundException;

    ChangeRequest deleteById(Integer id) throws ChangeRequestNotFoundException;
}

package com.ebatta.gclp.persistence.dao;

import java.util.List;

import com.ebatta.gclp.persistence.model.ChangeRequest;

public interface ChangeRequestDao {

    ChangeRequest findById(Integer id);

    ChangeRequest findByTitle(final String title);

    List<ChangeRequest> findAll();

    ChangeRequest create(ChangeRequest changeRequest);

    ChangeRequest update(ChangeRequest changeRequest);

    ChangeRequest delete(final ChangeRequest entity);

    ChangeRequest deleteById(Integer id);
}

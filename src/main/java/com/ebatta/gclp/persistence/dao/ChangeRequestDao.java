package com.ebatta.gclp.persistence.dao;

import java.util.List;

import com.ebatta.gclp.persistence.model.ChangeRequest;

public interface ChangeRequestDao {

    ChangeRequest findById(int id);

    ChangeRequest findByTitle(final String title);

    List<ChangeRequest> findAll();

    void create(ChangeRequest changeRequest);

    ChangeRequest update(ChangeRequest changeRequest);

    void deleteById(int id);
}

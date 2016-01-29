package com.ebatta.gclp.persistence.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.ebatta.gclp.persistence.model.ChangeRequest;

@Repository
public class ChangeRequestJpaDao extends AbstractJpaDAO<ChangeRequest> implements ChangeRequestDao {

    @PersistenceContext
    private EntityManager entityManager;

    public ChangeRequestJpaDao() {
        super();
        
        setClazz(ChangeRequest.class);
    }

    public ChangeRequest findByTitle(final String title) {
        final Query query = 
                entityManager.createQuery("from ChangeRequest where title = :title");
        query.setParameter("title", title);
        
        return (ChangeRequest) JpaResultHelper.getSingleResultOrNull(query);
    }

}

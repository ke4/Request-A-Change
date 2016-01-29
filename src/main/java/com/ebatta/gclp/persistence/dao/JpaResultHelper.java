package com.ebatta.gclp.persistence.dao;

import java.util.List;

import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;

public class JpaResultHelper {

    @SuppressWarnings("rawtypes")
    public static Object getSingleResultOrNull(Query query){
        List results = query.getResultList();
        if (results.isEmpty()) return null;
        else if (results.size() == 1) return results.get(0);
        throw new NonUniqueResultException();
    }
}
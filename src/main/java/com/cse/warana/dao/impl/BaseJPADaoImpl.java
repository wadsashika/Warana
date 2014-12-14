package com.cse.warana.dao.impl;

import com.cse.warana.dao.BaseJPADao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by Sashika
 * on Dec 03 0003, 2014.
 */
public class BaseJPADaoImpl<T> implements BaseJPADao<T> {

    @PersistenceContext
    protected EntityManager entityManager;

    @Override
    public T getEntity(Class<T> t, Object primaryKey) {
        return this.entityManager.find(t, primaryKey);
    }

    @Override
    public T saveEntity(T t) {
        return this.entityManager.merge(t);
    }

    @Override
    public void flush() {
        this.entityManager.flush();
    }

    @Override
    public void refresh(T t) {
        this.entityManager.refresh(t);
    }

    @Override
    public void deleteEntity(Class<T> t, Object primaryKey) {
        T tt = this.getEntity(t, primaryKey);
        this.entityManager.remove(tt);
    }

    @Override
    public void deleteEntity(T t) {
        this.entityManager.remove(t);
    }

    @Override
    public void persistEntity(T t) {
        this.entityManager.persist(t);
    }
}

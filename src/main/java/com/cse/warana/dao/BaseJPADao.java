package com.cse.warana.dao;

/**
 * Created by Sashika
 * on Dec 03 0003, 2014.
 */
public interface BaseJPADao<T> {

    public T getEntity(Class<T> t, Object primaryKey);

    public T saveEntity(T t);

    public void flush();

    public void refresh(T t);

    public void deleteEntity(Class<T> t, Object primaryKey);

    public void deleteEntity(T t);
}

package com.moon.senla.api;

import java.util.List;

public interface GenericDao<T> {

    void create(T entity);

    T read(int id);

    List<T> readAll();

    void update(T entity);

    void delete(T entity);

}

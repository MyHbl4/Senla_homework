package com.moon.senla;

import java.util.List;

public interface GenericDao<T> {

    void create(T entity);

    T read(Long id);

    List<T> getAll();

    T update(T entity);

    void delete(T entity);

    void delete(Long id);

}

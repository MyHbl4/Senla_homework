package com.moon.senla;

import java.util.List;

import com.moon.senla.entity.Book;

public class BookDao extends AbstractDao<Book> implements IBookDao{
    @Override
    public void create(Book entity) {

    }

    @Override
    public Book read(Long id) {
        return null;
    }

    @Override
    public List<Book> getAll() {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Book update(Book entity) {
        return null;
    }

    @Override
    public void delete(Book entity) {

    }
}

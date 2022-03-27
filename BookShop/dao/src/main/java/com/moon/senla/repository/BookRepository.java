package com.moon.senla.repository;

import com.moon.senla.entity.Book;
import java.util.List;

public interface BookRepository {

    List<Book> getAll();

    void removeBooks(List<Book> books);

    boolean checkBookInBooks(Book myBook);

    void restoreBook(Book myBook);
}

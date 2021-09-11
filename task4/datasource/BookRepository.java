package task4.datasource;

import task4.model.Book;

public interface BookRepository {

    void addBook(Book book);

    void removeBook(Book books);

    Book findBookById(long id);

    void printBookRepository();

}

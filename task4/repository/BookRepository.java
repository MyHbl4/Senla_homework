package task4.repository;

import task4.model.Book;
import task4.util.BookArrayList;

public interface BookRepository {

  BookArrayList getAll();

  Book findBookById(int id);

  Book findBookByTitle(String tiltle);

  Book[] getArrayBook();

  int getCountOldBook();

  Book[] getArrayOldBook();
}

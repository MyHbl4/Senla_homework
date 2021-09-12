package task4.datasource;

import task4.model.Book;

public class BookRepositoryImpl implements BookRepository {

  private final BookDataSource bookDataSource;

  public BookRepositoryImpl(BookDataSource bookDataSource) {

    this.bookDataSource = bookDataSource;
  }

  @Override
  public void addBook(Book book) {
    bookDataSource.getBooks().add(book);
    System.out.println("The book has been added!");
  }

  @Override
  public void inStock(int id) {
    bookDataSource.getBooks().get(id - 1).setBookStatusInStock();
    System.out.println("The book is in stock!");
  }

  @Override
  public void outOfStock(int id) {
    bookDataSource.getBooks().get(id - 1).setBookStatusOutOfStock();
    System.out.println("The book is out of stock!");
  }

  @Override
  public void removeBook(int id) {
    for (int i = 0; i < bookDataSource.getBooks().size(); i++) {
      if (bookDataSource.getBooks().get(i).getId() == id) {
        bookDataSource.getBooks().remove(i);
        System.out.println("The book has been deleted!");
      }
    }
  }

  @Override
  public Book findBookById(int id) {
    return bookDataSource.getBooks().get(id - 1);
  }

  @Override
  public void printBookRepository() {
    System.out.println("List of all books:");
    bookDataSource.getBooks().print();
  }
}

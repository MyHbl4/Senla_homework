package task4.datasource;

import task4.model.Availability;
import task4.model.Book;

public class BookRepositoryImpl implements BookRepository {

    private final BookDataSource bookDataSource;

    public BookRepositoryImpl(BookDataSource bookDataSource) {
        this.bookDataSource = bookDataSource;
    }


    @Override
    public void addBook(Book book) {
        System.out.println("Книга добавлена!");
        bookDataSource.getBooks().add(book);
    }

    @Override
    public void removeBook(Book book) {
        System.out.println("Книга удалена!");
        bookDataSource.getBooks().remove(bookDataSource.getBooks().indexOf(book));
        book.setAvailability(Availability.OUT_OF_STOCK);
    }

    @Override
    public Book findBookById(long id) {
        int index = (int)id;
        return bookDataSource.getBooks().get(index);
    }

    @Override
    public void printBookRepository() {
        System.out.println("Список всех книг:");
        bookDataSource.getBooks().print();
    }

}

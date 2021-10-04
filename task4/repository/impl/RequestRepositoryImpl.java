package task4.repository.impl;

import java.util.List;
import task4.datasource.BookDataSource;
import task4.datasource.RequestDataSource;
import task4.model.Book;
import task4.model.Request;
import task4.repository.RequestRepository;

public class RequestRepositoryImpl implements RequestRepository {
  private final RequestDataSource requestDataSource;
  private final BookDataSource bookDataSource;

  public RequestRepositoryImpl(RequestDataSource requestDataSource, BookDataSource bookDataSource) {
    this.requestDataSource = requestDataSource;
    this.bookDataSource = bookDataSource;
  }

  @Override
  public List<Request> getAll() {
    return requestDataSource.getRequest();
  }

  @Override
  public void addRequest(long bookId) {
    String title = null;
    int goodJob = 0;
    for (int i = 0; i < getAll().size(); i++) {
      if (getAll().get(i).getBookId() == bookId) {
        getAll().get(i).setCount(getAll().get(i).getCount() + 1);
        goodJob = 1;
      }
    }
    if (goodJob == 0) {
      for (Book book : bookDataSource.getBooks()) {
        if (book.getId() == bookId) {
          title = book.getTitle();
        }
      }
      getAll().add(new Request(bookId, title));
    }
  }

  @Override
  public Request findRequestById(int id) {
    return requestDataSource.findRequestById(id);
  }
}

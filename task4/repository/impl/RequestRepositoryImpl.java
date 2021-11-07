package task4.repository.impl;

import java.util.List;
import task4.DI.annotations.InjectByType;
import task4.datasource.BookDataSource;
import task4.datasource.RequestDataSource;
import task4.jdbc.RequestDAO;
import task4.model.Book;
import task4.model.Request;
import task4.repository.RequestRepository;

public class RequestRepositoryImpl implements RequestRepository {
  @InjectByType private RequestDataSource requestDataSource;
  @InjectByType private BookDataSource bookDataSource;
  @InjectByType private RequestDAO requestDAO;

  @Override
  public List<Request> getAll() {
    return requestDataSource.getRequest();
  }

  @Override
  public void addRequest(long bookId) {
    String title = null;
    int goodJob = 0;
    for (Request request : getAll()) {
      if (request.getBookId() == bookId) {
        requestDAO.update(Math.toIntExact(request.getId()));
        goodJob = 1;
      }
    }
    if (goodJob == 0) {
      for (Book book : bookDataSource.getBooks()) {
        if (book.getId() == bookId) {
          title = book.getTitle();
        }
      }
      requestDAO.create(new Request(bookId, title));
    }
  }

  @Override
  public Request findRequestById(int id) {
    return requestDataSource.findRequestById(id);
  }
}

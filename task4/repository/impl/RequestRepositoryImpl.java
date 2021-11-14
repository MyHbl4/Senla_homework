package task4.repository.impl;

import java.util.List;
import task4.DI.annotations.InjectByType;
import task4.jdbc.BookDAO;
import task4.jdbc.RequestDAO;
import task4.model.Book;
import task4.model.Request;
import task4.repository.RequestRepository;

public class RequestRepositoryImpl implements RequestRepository {
  @InjectByType private RequestDAO requestDAO;
  @InjectByType private BookDAO bookDAO;

  @Override
  public List<Request> getAll() {
    return requestDAO.readAll();
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
      for (Book book : bookDAO.readAll()) {
        if (book.getId() == bookId) {
          title = book.getTitle();
        }
      }
      requestDAO.create(new Request(bookId, title));
    }
  }

  @Override
  public Request findRequestById(int id) {
    return requestDAO.read(id);
  }
}

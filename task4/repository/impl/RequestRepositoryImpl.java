package task4.repository.impl;

import java.util.List;
import task4.DI.injector.BeanFactory;
import task4.datasource.BookDataSource;
import task4.datasource.RequestDataSource;
import task4.datasource.impl.RequestDataSourceImpl;
import task4.model.Book;
import task4.model.Request;
import task4.repository.RequestRepository;

public class RequestRepositoryImpl implements RequestRepository {
  private final RequestDataSource requestDataSource =
      BeanFactory.getInstance().getBean(RequestDataSourceImpl.class);
  private final BookDataSource bookDataSource =
      BeanFactory.getInstance().getBean(BookDataSource.class);

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

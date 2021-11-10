package com.moon.senla.impl;

import com.moon.senla.Book;
import com.moon.senla.BookDAO;
import com.moon.senla.Request;
import com.moon.senla.RequestDAO;
import com.moon.senla.RequestRepository;
import com.moon.senla.annotations.InjectByType;
import java.util.List;

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

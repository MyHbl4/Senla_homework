package com.moon.senla.impl;

import java.util.List;

import com.moon.senla.BookDAO;
import com.moon.senla.RequestDAO;
import com.moon.senla.RequestRepository;
import com.moon.senla.annotations.InjectByType;
import com.moon.senla.entity.Book;
import com.moon.senla.entity.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RequestRepositoryImpl implements RequestRepository {
  private static final Logger logger = LoggerFactory.getLogger(RequestRepositoryImpl.class);
  @InjectByType private RequestDAO requestDAO;
  @InjectByType private BookDAO bookDAO;

  @Override
  public List<Request> getAll() {
    return requestDAO.readAll();
  }

  @Override
  public void addRequest(long bookId) {
    try {
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
      logger.info(
          "Method completed - " + Thread.currentThread().getStackTrace()[1].getMethodName());
    } catch (Exception e) {
      logger.warn(
          "Failed to execute the method - "
              + Thread.currentThread().getStackTrace()[1].getMethodName(),
          e);
    }
  }

  @Override
  public Request findRequestById(int id) {
    return requestDAO.read(id);
  }
}

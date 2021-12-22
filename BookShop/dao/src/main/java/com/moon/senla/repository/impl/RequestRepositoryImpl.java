package com.moon.senla.repository.impl;

import com.moon.senla.dao.BookDao;
import com.moon.senla.dao.RequestDao;
import com.moon.senla.entity.Book;
import com.moon.senla.entity.Request;
import com.moon.senla.repository.RequestRepository;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RequestRepositoryImpl implements RequestRepository {
    private static final Logger LOGGER = LoggerFactory.getLogger(RequestRepositoryImpl.class);
    private RequestDao requestDAO;
    private BookDao bookDAO;

    @Autowired
    public RequestRepositoryImpl(RequestDao requestDAO, BookDao bookDAO) {
        this.requestDAO = requestDAO;
        this.bookDAO = bookDAO;
    }

    @Override
    public List<Request> getAll() {
        return requestDAO.readAll();
    }

    @Override
    public void addRequest(long bookId) {
        List<Book> list = bookDAO.readAll();
        boolean isMath = list.stream().anyMatch(u -> u.getId() == (bookId));
        if (isMath) {
            try {
                int goodJob = 0;
                for (Request request : requestDAO.readAll()) {
                    if (request.getBook().getId() == bookId) {
                        request.setCount(request.getCount() + 1);
                        requestDAO.update(request);
                        goodJob = 1;
                    }
                }
                if (goodJob == 0) {
                    Book bookr = bookDAO.read((int) bookId);
                    requestDAO.create(new Request(bookr));
                }
                LOGGER.info(
                    "Method completed - " + Thread.currentThread()
                        .getStackTrace()[1].getMethodName());
            } catch (Exception e) {
                LOGGER.warn(
                    "Failed to execute the method - "
                        + Thread.currentThread().getStackTrace()[1].getMethodName(),
                    e);
            }
        }
    }

    @Override
    public Request findRequestById(int id) {
        return requestDAO.read(id);
    }
}

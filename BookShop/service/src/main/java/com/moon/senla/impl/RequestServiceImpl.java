package com.moon.senla.impl;

import java.util.Comparator;
import java.util.List;

import com.moon.senla.BookDAO;
import com.moon.senla.RequestRepository;
import com.moon.senla.RequestService;
import com.moon.senla.annotations.InjectByType;
import com.moon.senla.entity.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RequestServiceImpl implements RequestService {
  private static final Logger logger = LoggerFactory.getLogger(BookDAO.class);
  @InjectByType private RequestRepository requestRepository;

  @Override
  public List<Request> getAll() {
    return requestRepository.getAll();
  }

  @Override
  public void addRequest(long bookId) {
    try {
      requestRepository.addRequest(bookId);
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
  public List<Request> sortRequestByCount() {
    List<Request> sortRequests = null;
    try {
      sortRequests = requestRepository.getAll();
      sortRequests.sort(Comparator.comparingInt(Request::getCount));
      logger.info(
          "Method completed - " + Thread.currentThread().getStackTrace()[1].getMethodName());
    } catch (Exception e) {
      logger.warn(
          "Failed to execute the method - "
              + Thread.currentThread().getStackTrace()[1].getMethodName(),
          e);
    }
    return sortRequests;
  }

  @Override
  public List<Request> sortRequestByTitle() {
    List<Request> sortRequests = null;
    try {
      sortRequests = requestRepository.getAll();
      sortRequests.sort(Comparator.comparing(Request::getTitle));
      logger.info(
          "Method completed - " + Thread.currentThread().getStackTrace()[1].getMethodName());
    } catch (Exception e) {
      logger.warn(
          "Failed to execute the method - "
              + Thread.currentThread().getStackTrace()[1].getMethodName(),
          e);
    }
    return sortRequests;
  }
}

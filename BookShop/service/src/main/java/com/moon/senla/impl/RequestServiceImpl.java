package com.moon.senla.impl;

import com.moon.senla.Request;
import com.moon.senla.RequestRepository;
import com.moon.senla.RequestService;
import com.moon.senla.annotations.InjectByType;
import java.util.Comparator;
import java.util.List;

public class RequestServiceImpl implements RequestService {
  @InjectByType private RequestRepository requestRepository;

  @Override
  public List<Request> getAll() {
    return requestRepository.getAll();
  }

  @Override
  public void addRequest(long bookId) {
    requestRepository.addRequest(bookId);
  }

  @Override
  public List<Request> sortRequestByCount() {
    List<Request> sortRequests = requestRepository.getAll();
    sortRequests.sort(Comparator.comparingInt(Request::getCount));
    return sortRequests;
  }

  @Override
  public List<Request> sortRequestByTitle() {
    List<Request> sortRequests = requestRepository.getAll();
    sortRequests.sort(Comparator.comparing(Request::getTitle));
    return sortRequests;
  }
}

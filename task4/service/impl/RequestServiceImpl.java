package task4.service.impl;

import task4.model.Request;
import task4.repository.RequestRepository;
import task4.service.RequestService;

public class RequestServiceImpl implements RequestService {
  private final RequestRepository requestRepository;

  public RequestServiceImpl(RequestRepository requestRepository) {
    this.requestRepository = requestRepository;
  }

  @Override
  public void addRequest(Request request) {
    requestRepository.getAll().add(request);
  }
}

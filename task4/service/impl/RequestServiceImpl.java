package task4.service.impl;

import java.util.Arrays;
import task4.comporator.request.SortRequestByCount;
import task4.comporator.request.SortRequestByTitle;
import task4.model.Request;
import task4.repository.RequestRepository;
import task4.service.RequestService;

public class RequestServiceImpl implements RequestService {
  private final RequestRepository requestRepository;

  public RequestServiceImpl(RequestRepository requestRepository) {
    this.requestRepository = requestRepository;
  }

  @Override
  public void printRequestRepository() {
    System.out.println("List of all request:");
    requestRepository.getAll().print();
  }

  @Override
  public void outputArray(Request[] requests) {
      for (Request request : requests) {
        System.out.println(request);
      }
    }

  @Override
  public Request[] sortRequestByCount() {
    Request[] sortRequest = requestRepository.getArray(requestRepository.getAll());
    Arrays.sort(sortRequest, new SortRequestByCount());
    return sortRequest;
  }

  @Override
  public Request[] sortRequestByTitle() {
    Request[] sortRequest = requestRepository.getArray(requestRepository.getAll());
    Arrays.sort(sortRequest, new SortRequestByTitle());
    return sortRequest;
  }
}

package task4.service.impl;

import task4.repository.RequestRepository;
import task4.service.RequestService;

public class RequestServiceImpl implements RequestService {
  private final RequestRepository requestRepository;

  public RequestServiceImpl(RequestRepository requestRepository) {
    this.requestRepository = requestRepository;
  }

  @Override
  public void sortRequestByCount() {
    requestRepository.getAll().sort((r1, r2) -> r1.getCount() - r2.getCount());
    requestRepository.getAll().stream().forEach(System.out::println);
  }

  @Override
  public void sortRequestByTitle() {
    requestRepository.getAll().sort((r1, r2) -> r1.getTitle().compareTo(r2.getTitle()));
    requestRepository.getAll().stream().forEach(System.out::println);
  }
}

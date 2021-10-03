package task4.service.impl;

import static task4.UI.Constant.FILE_REQUESTS;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Comparator;
import java.util.List;
import task4.model.Request;
import task4.repository.BookRepository;
import task4.repository.RequestRepository;
import task4.service.RequestService;

public class RequestServiceImpl implements RequestService {
  private final RequestRepository requestRepository;
  private  final BookRepository bookRepository;

  public RequestServiceImpl(RequestRepository requestRepository,BookRepository bookRepository) {
    this.requestRepository = requestRepository;
    this.bookRepository = bookRepository;
  }

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
    requestRepository.getAll().sort(Comparator.comparingInt(Request::getCount));
    return sortRequests;
  }

  @Override
  public List<Request> sortRequestByTitle() {
    List<Request> sortRequests = requestRepository.getAll();
    requestRepository.getAll().sort(Comparator.comparing(Request::getTitle));
    return sortRequests;
  }

  @Override
  public void updateRequestCsv() {
    try {
      PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(FILE_REQUESTS)));
      for (Request request : requestRepository.getAll()) {
        writer.println(request.getId() + "|" + request.getCount() + "|" + request.getBookId() + "|" + request.getTitle());
      }
      writer.flush();
      writer.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void downloadRequestCsv() {
    try {
      try (BufferedReader reader = new BufferedReader(new FileReader(FILE_REQUESTS))) {
        String someRequest;
        requestRepository.getAll().clear();
        while ((someRequest = reader.readLine()) != null) {
          String[] values = someRequest.split("\\|");
          requestRepository
              .getAll()
              .add(new Request(Long.parseLong(values[0]), Integer.parseInt(values[1]), Long.parseLong(values[2]), values[3]));
        }
      }
    } catch (IOException e) {
      System.out.println("Loading error");
    }
  }
}

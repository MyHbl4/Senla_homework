package task4.service.impl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import task4.model.Availability;
import task4.model.Book;
import task4.model.Request;
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

  @Override
  public void updateRequestCsv() {
    String fileName = "requestdata.csv";
    try {
      PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(fileName)));
      for (Request request : requestRepository.getAll()) {
        writer.println(
            request.getId()
                + "|"
                + request.getCount()
                + "|"
                + request.getTitle());
      }
      writer.flush();
      writer.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void downloadRequestCsv() {
    String fileName = "requestdata.csv";
    try {
      try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
        String someRequest;
        requestRepository.getAll().clear();
        while ((someRequest = reader.readLine()) != null) {
          String[] values = someRequest.split("\\|");
          requestRepository
              .getAll()
              .add(
                  new Request(
                      Long.parseLong(values[0]),
                      Integer.parseInt(values[1]),
                      values[2]));
        }
      }
    } catch (IOException e) {
      System.out.println("Loading error");
    }
  }
}

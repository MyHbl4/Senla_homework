package task4.service;

import java.util.List;
import task4.model.Request;

public interface RequestService {

  List<Request> getAll();

  void addRequest(long bookId);

  List<Request> sortRequestByCount();

  List<Request> sortRequestByTitle();

  void writerRequestBd();

  void readRequestBd();

//  void updateRequestCsv();
//
//  void downloadRequestCsv();
}

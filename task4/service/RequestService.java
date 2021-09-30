package task4.service;

import java.util.List;
import task4.model.Request;

public interface RequestService {

  List<Request> getAll();

  void updateRequestCsv();

  void downloadRequestCsv();
}

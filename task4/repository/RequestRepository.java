package task4.repository;

import java.util.List;
import task4.model.Request;

public interface RequestRepository {

  List<Request> getAll();

  void addRequest(long bookId);

  Request findRequestById(int id);
}

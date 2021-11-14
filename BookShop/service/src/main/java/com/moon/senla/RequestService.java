package com.moon.senla;

import com.moon.senla.entity.Request;
import java.util.List;

public interface RequestService {

  List<Request> getAll();

  void addRequest(long bookId);

  List<Request> sortRequestByCount();

  List<Request> sortRequestByTitle();
}

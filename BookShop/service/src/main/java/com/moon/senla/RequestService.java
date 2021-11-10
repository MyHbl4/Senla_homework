package com.moon.senla;

import java.util.List;

public interface RequestService {

  List<Request> getAll();

  void addRequest(long bookId);

  List<Request> sortRequestByCount();

  List<Request> sortRequestByTitle();

  void writerRequestBd();

  void readRequestBd();
}

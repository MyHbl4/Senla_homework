package com.moon.senla;

import java.util.List;

public interface RequestRepository {

  List<Request> getAll();

  void addRequest(long bookId);

  Request findRequestById(int id);
}

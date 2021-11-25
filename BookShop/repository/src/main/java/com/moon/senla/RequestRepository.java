package com.moon.senla;

import java.util.List;

import com.moon.senla.entity.Request;

public interface RequestRepository {

  List<Request> getAll();

  void addRequest(long bookId);

  Request findRequestById(int id);
}

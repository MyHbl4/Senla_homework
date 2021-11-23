package com.moon.senla.service;

import java.util.List;

import com.moon.senla.entity.Request;

public interface RequestService {

  List<Request> getAll();

  void addRequest(long bookId);

  List<Request> sortRequestByCount();

  List<Request> sortRequestByTitle();
}

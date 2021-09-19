package task4.service;

import task4.model.Request;

public interface RequestService {

  void printRequestRepository();

  void outputArray(Request[] requests);

  Request[] sortRequestByCount();

  Request[] sortRequestByTitle();
}

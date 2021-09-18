package task4.repository.impl;

import task4.datasource.RequestDataSource;
import task4.repository.RequestRepository;
import task4.util.RequestArrayList;

public class RequestRepositoryImpl implements RequestRepository {
  private final RequestDataSource requestDataSource;

  public RequestRepositoryImpl(RequestDataSource requestDataSource) {
    this.requestDataSource = requestDataSource;
  }

  @Override
  public RequestArrayList getAll() {
    return requestDataSource.getRequest();
  }
}

package task4.repository.impl;

import java.util.List;
import task4.datasource.RequestDataSource;
import task4.model.Request;
import task4.repository.RequestRepository;

public class RequestRepositoryImpl implements RequestRepository {
  private final RequestDataSource requestDataSource;

  public RequestRepositoryImpl(RequestDataSource requestDataSource) {
    this.requestDataSource = requestDataSource;
  }

  @Override
  public List<Request> getAll() {
    return requestDataSource.getRequest();
  }
}

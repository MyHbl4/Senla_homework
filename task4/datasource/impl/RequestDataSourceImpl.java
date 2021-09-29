package task4.datasource.impl;

import java.util.ArrayList;
import java.util.List;
import task4.datasource.RequestDataSource;
import task4.model.Request;

public class RequestDataSourceImpl implements RequestDataSource {
  private final List<Request> requests = new ArrayList<>();

  public RequestDataSourceImpl() {
    initData();
  }

  public void initData() {}

  @Override
  public List<Request> getRequest() {
    return requests;
  }
}

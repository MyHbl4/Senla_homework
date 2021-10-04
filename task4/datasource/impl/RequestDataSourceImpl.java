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

  public Request findRequestById(int id) {
    Request request = null;
    for (int i = 0; i < getRequest().size(); i++) {
      if (getRequest().get(i).getId() == id) {
        request = getRequest().get(i);
      }
    }
    return request;
  }
}

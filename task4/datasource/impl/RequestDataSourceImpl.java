package task4.datasource.impl;

import java.util.ArrayList;
import java.util.List;
import task4.DI.annotations.InjectByType;
import task4.datasource.RequestDataSource;
import task4.jdbc.RequestDAO;
import task4.model.Request;

public class RequestDataSourceImpl implements RequestDataSource {
  private final List<Request> requests = new ArrayList<>();
  @InjectByType private RequestDAO requestDAO;

  public RequestDataSourceImpl() {
    initData();
  }

  public void initData() {}

  @Override
  public List<Request> getRequest() {
    return requestDAO.readAll();
  }

  @Override
  public Request findRequestById(int id) {
    return requestDAO.read(id);
  }
}

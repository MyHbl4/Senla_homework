package task4.datasource.impl;

import task4.datasource.RequestDataSource;
import task4.util.RequestArrayList;

public class RequestDataSourceImpl implements RequestDataSource {
  private final RequestArrayList requests = new RequestArrayList();

  public RequestDataSourceImpl() {
    initData();
  }

  public void initData() {

  }

  @Override
  public RequestArrayList getRequest() {
    return requests;
  }
}

package task4.repository;

import task4.model.Order;
import task4.model.Request;
import task4.util.OrderArrayList;
import task4.util.RequestArrayList;

public interface RequestRepository {

  RequestArrayList getAll();

  Request[] getArray(RequestArrayList request);
}

package com.moon.senla.action.request.sortrequest;

import java.util.List;
import com.moon.senla.action.AbstractAction;
import com.moon.senla.action.Manager;
import com.moon.senla.Request;

public class SortRequestByCountAction extends AbstractAction {

  public SortRequestByCountAction(Manager manager) {
    super(manager);
  }

  @Override
  public void execute() {
    List<Request> sortRequests = manager.getRequestService().sortRequestByCount();
    printOut.printList(sortRequests);
  }
}

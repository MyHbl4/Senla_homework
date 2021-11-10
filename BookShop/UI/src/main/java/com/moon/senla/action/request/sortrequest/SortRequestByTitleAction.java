package com.moon.senla.action.request.sortrequest;

import java.util.List;
import com.moon.senla.action.AbstractAction;
import com.moon.senla.action.Manager;
import com.moon.senla.Request;

public class SortRequestByTitleAction extends AbstractAction {

  public SortRequestByTitleAction(Manager manager) {
    super(manager);
  }

  @Override
  public void execute() {
    List<Request> sortRequests = manager.getRequestService().sortRequestByTitle();
    printOut.printList(sortRequests);
  }
}

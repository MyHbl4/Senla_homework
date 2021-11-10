package task4.UI.action.request.sortrequest;

import java.util.List;
import task4.UI.action.AbstractAction;
import task4.UI.action.Manager;
import task4.model.Request;

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

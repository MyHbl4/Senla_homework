package task4.UI.action.request.sortrequest;

import java.util.List;
import task4.UI.action.AbstractAction;
import task4.model.Request;

public class SortRequestByCountAction extends AbstractAction {

  @Override
  public void execute() {
    List<Request> sortRequests = manager.getRequestService().sortRequestByCount();
    printOut.printRequest(sortRequests);
  }
}

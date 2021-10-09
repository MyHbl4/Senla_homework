package task4.UI.action.orderAction;

import task4.UI.action.AbstractAction;
import task4.UI.action.Manager;
import task4.model.Order;
import task4.util.CustomException;

public class OrderInfoAction extends AbstractAction {

  public OrderInfoAction(Manager manager) {
    super(manager);
  }

  @Override
  public void execute() {
    System.out.println("Enter ID of the order you want to find out information about");
    int id;
    boolean isValid = true;
    while (isValid) {
      try {
        id = customScanner.getInt();
        check.checkOrder(id);
        Order order = manager.getOrderService().findOrderById(id);
        printOut.printEntity(order);
        isValid = false;
      } catch (CustomException e) {
        System.out.println(" Value is incorrect");
      }
    }
  }
}

package task4.UI.action.order;

import task4.UI.action.AbstractAction;
import task4.UI.action.IAction;
import task4.exception.CheckOfData;
import task4.exception.CustomException;
import task4.exception.CustomScanner;
import task4.model.Order;

public class OrderInfoAction extends AbstractAction implements IAction {
  private CheckOfData checkOfData;
  private CustomScanner customScanner;

  public OrderInfoAction(CheckOfData checkData, CustomScanner customScanner) {
    this.checkOfData = checkData;
    this.customScanner = customScanner;
  }

  @Override
  public void execute() {
    System.out.println("Enter ID of the order you want to find out information about");
    int id;
    boolean isValid = true;
    while (isValid) {
      try {
        id = customScanner.getInt();
        checkOfData.checkOrder(id);
        Order order = manager.getOrderService().findOrderById(id);
        System.out.print(
            "Order ID: "
                + order.getId()
                + ", Customer: '"
                + order.getCustomerName()
                + "', Price: "
                + order.getPrice()
                + ", Status: "
                + order.getOrderStatus()
                + ", ");
        order.showBooks();
        isValid = false;
      } catch (CustomException e) {
        System.out.println(" Value is incorrect");
      }
    }
  }
}

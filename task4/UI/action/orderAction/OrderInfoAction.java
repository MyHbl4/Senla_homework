package task4.UI.action.orderAction;

import task4.UI.action.AbstractAction;
import task4.UI.action.IAction;
import task4.model.Order;
import task4.util.CheckOfData;
import task4.util.CustomException;
import task4.util.CustomScanner;

public class OrderInfoAction extends AbstractAction {
  private CheckOfData checkOfData;


  public OrderInfoAction(CheckOfData checkData) {
    this.checkOfData = checkData;
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

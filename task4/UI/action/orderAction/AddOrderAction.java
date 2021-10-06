package task4.UI.action.orderAction;

import java.util.ArrayList;
import java.util.List;
import task4.UI.action.AbstractAction;
import task4.UI.action.Manager;
import task4.model.Book;
import task4.model.Order;
import task4.exceptions.CustomException;

public class AddOrderAction extends AbstractAction {

  public AddOrderAction(Manager manager) {
    super(manager);
  }

  @Override
  public void execute() {
    String customerName;
    int amount;
    int id;
    List<Book> books = new ArrayList<>();
    System.out.println("Enter the customer's name");
    customerName = customScanner.getString();
    System.out.println("Enter the number of books you want to buy");
    amount = customScanner.getInt();
    for (int i = 0; i < amount; i++) {
      System.out.println("Enter ID of the book you want to buy");
      boolean isValid = true;
      while (isValid) {
        try {
          id = customScanner.getInt();
          check.checkBook(id);
          books.add(manager.getBookService().findBookById(id));
          if (i == amount - 1) {
            manager.getOrderService().addOrder(new Order(customerName, books));
            System.out.println("Order has been added");
          }
          isValid = false;
        } catch (CustomException e) {
          System.out.println(" Value is incorrect");
        }
      }
    }
    manager.getOrderService().writeOrderBd();
    manager.getBookService().writeBookBd();
    manager.getRequestService().writerRequestBd();
  }
}

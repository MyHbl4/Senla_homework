package task4.util;

import task4.UI.action.Manager;

public class Check {
  public Check() {}

  public void checkOrder(int id) throws CustomException {
    if (id > 0 && new Manager().getOrderService().findOrderById(id) != null) {
      return;
    }
    throw new CustomException();
  }

  public void checkBook(int id) throws CustomException {
    if (id > 0 && new Manager().getBookService().findBookById(id) != null) {
      return;
    }
    throw new CustomException();
  }
}

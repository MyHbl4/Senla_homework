package task4.exception;

import task4.UI.action.AbstractAction;

public class CheckOfData extends AbstractAction {

  public CheckOfData() {
  }

  public void checkOrder(int id) throws CustomException {
    if (id > 0 && manager.getOrderService().findOrderById(id) != null) {
      return;
    }
    throw new CustomException();
  }

  public void checkBook(int id) throws CustomException {
    if (id > 0 && manager.getBookService().findBookById(id) != null) {
      return;
    }
    throw new CustomException();
  }

  @Override
  public void execute() {

  }
}

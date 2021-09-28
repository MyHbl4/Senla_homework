package task4.exception;

import task4.UI.action.CreateManager;

public class CheckOfData extends CreateManager {

  public CheckOfData() {
  }

  public void checkOrder(int id) throws CustomException {
    if (id > 0 && manager.findOrderById(id) != null) {
      return;
    }
    throw new CustomException();
  }

  public void checkBook(int id) throws CustomException {
    if (id > 0 && manager.findBookById(id) != null) {
      return;
    }
    throw new CustomException();
  }
}

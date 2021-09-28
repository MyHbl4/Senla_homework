package task4.UI.action.book;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import task4.UI.action.CreateManager;
import task4.UI.action.IAction;
import task4.exception.CheckOfData;
import task4.exception.CustomException;
import task4.exception.CustomScanner;

public class BookInfo extends CreateManager implements IAction {
  private CheckOfData checkOfData;
  private CustomScanner customScanner;

  public BookInfo(CheckOfData checkOfData, CustomScanner customScanner) {
    this.checkOfData = checkOfData;
    this.customScanner = customScanner;
  }

  @Override
  public void execute() {
    System.out.println("Enter ID of the book you want to find out information about");
    int id;
    boolean isValid = true;
    while (isValid) {
      try {
        id = customScanner.getInt();
        checkOfData.checkBook(id);
        manager.bookInfoById(id);;
        isValid = false;
      } catch (CustomException e) {
        System.out.println(" Value is incorrect");
      }
    }
  }
}

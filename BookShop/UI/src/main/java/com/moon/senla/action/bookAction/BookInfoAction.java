package com.moon.senla.action.bookAction;

import com.moon.senla.entity.Book;
import com.moon.senla.CustomException;
import com.moon.senla.action.AbstractAction;
import com.moon.senla.action.Manager;

public class BookInfoAction extends AbstractAction {

  public BookInfoAction(Manager manager) {
    super(manager);
  }

  @Override
  public void execute() {
    System.out.println("Enter ID of the book you want to find out information about");
    int id;
    boolean isValid = true;
    while (isValid) {
      try {
        id = customScanner.getInt();
        check.checkBook(id);
        Book book = manager.getBookService().findBookById(id);
        printOut.printEntity(book);
        isValid = false;
      } catch (CustomException e) {
        System.out.println(" Value is incorrect");
      }
    }
  }
}

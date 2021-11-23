package com.moon.senla.action.bookAction;

import com.moon.senla.CustomException;
import com.moon.senla.action.AbstractAction;
import com.moon.senla.action.Manager;
import org.springframework.stereotype.Component;

@Component
public class RemoveBookAction extends AbstractAction {

  public RemoveBookAction(Manager manager) {
    super(manager);
  }

  @Override
  public void execute() {
    System.out.println("Enter ID of the book to remove from availability");
    int id;
    boolean isValid = true;
    while (isValid) {
      try {
        id = customScanner.getInt();
        check.checkBook(id);
        manager.getBookService().removeBook(id);
        System.out.println("The book has been removed");
        isValid = false;
      } catch (CustomException e) {
        System.out.println(" Value is incorrect");
      }
    }
  }
}

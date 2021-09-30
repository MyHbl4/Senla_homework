package task4.UI;

import static task4.UI.Constant.*;

import task4.UI.action.bookAction.AddBookAction;
import task4.UI.action.bookAction.BookInfoAction;
import task4.UI.action.bookAction.RemoveBookAction;
import task4.UI.action.bookAction.sortbook.SortBookByAvailabilityAction;
import task4.UI.action.bookAction.sortbook.SortBookByPriceAction;
import task4.UI.action.bookAction.sortbook.SortBookByPublicationAction;
import task4.UI.action.bookAction.sortbook.SortBookByTitleAction;
import task4.UI.action.bookAction.sortbook.SortOldBookByDateAction;
import task4.UI.action.bookAction.sortbook.SortOldBookByPriceAction;
import task4.UI.action.orderAction.AddOrderAction;
import task4.UI.action.orderAction.AmountEarnedFundsAction;
import task4.UI.action.orderAction.CancelOrderAction;
import task4.UI.action.orderAction.CloseOrderAction;
import task4.UI.action.orderAction.CompletedOrderAction;
import task4.UI.action.orderAction.OrderInfoAction;
import task4.UI.action.orderAction.sortorder.SortCompletedOrderByExecutionDateAction;
import task4.UI.action.orderAction.sortorder.SortCompletedOrderByPriceAction;
import task4.UI.action.orderAction.sortorder.SortOrderByExecurionDateAction;
import task4.UI.action.orderAction.sortorder.SortOrderByPriceAction;
import task4.UI.action.orderAction.sortorder.SortOrderByStatusAction;
import task4.UI.action.request.AddRequestAction;
import task4.UI.action.request.sortrequest.SortRequestByCountAction;
import task4.UI.action.request.sortrequest.SortRequestByTitleAction;
import task4.exception.CheckOfData;
import task4.exception.CustomScanner;

public class Builder {

  private static Builder instance;
  private Menu rootMenu;

  private Builder() {}

  public static Builder getInstance() {
    if (instance == null) {
      instance = new Builder();
    }
    return instance;
  }

  public Menu getRootMenu() {
    return rootMenu;
  }

  public void buildMenu() {
    rootMenu = new Menu();
    rootMenu.setName(ROOT_MENU);
    rootMenu.addMenuItem(
        new MenuItem(
            "1 - Books", () -> System.out.println("You are in the book menu"), createBookMenu()));
    rootMenu.addMenuItem(
        new MenuItem(
            "2 - Orders",
            () -> System.out.println("You are in the order menu"),
            createOrderMenu()));
    rootMenu.addMenuItem(
        new MenuItem(
            "3 - Requests\n0 - Exit",
            () -> System.out.println("You are in the request menu"),
            createRequestsMenu()));
  }

  private Menu createBookMenu() {
    Menu rootMenu = new Menu();
    rootMenu.setName(BOOK_MENU);
    rootMenu.addMenuItem(
        new MenuItem(
            "1 - View all books",
            () -> System.out.println("Select the sorting type"),
            createSortBookMenu()));
    rootMenu.addMenuItem(
        new MenuItem(
            "2 - View stale books",
            () -> System.out.println("Select the sorting type"),
            createSortStaleBookMenu()));
    rootMenu.addMenuItem(
        new MenuItem("3 - Add book", new AddBookAction(new CustomScanner()), getRootMenu()));
    rootMenu.addMenuItem(
        new MenuItem(
            "4 - Remove book",
            new RemoveBookAction(new CheckOfData(), new CustomScanner()),
            getRootMenu()));
    rootMenu.addMenuItem(
        new MenuItem("5 - Back\n0 - Exit", () -> System.out.println("Back"), getRootMenu()));
    return rootMenu;
  }

  private Menu createSortBookMenu() {
    Menu rootMenu = new Menu();
    rootMenu.setName(SORT_BOOKS);
    rootMenu.addMenuItem(
        new MenuItem("1 - Sorting by alphabetically", new SortBookByTitleAction(), getRootMenu()));
    rootMenu.addMenuItem(
        new MenuItem(
            "2 - Sorting by publication date", new SortBookByPublicationAction(), getRootMenu()));
    rootMenu.addMenuItem(
        new MenuItem("3 - Sorting by price", new SortBookByPriceAction(), getRootMenu()));
    rootMenu.addMenuItem(
        new MenuItem(
            "4 - Sorting by availability", new SortBookByAvailabilityAction(), getRootMenu()));
    rootMenu.addMenuItem(
        new MenuItem(
            "5 - Book information",
            new BookInfoAction(new CheckOfData(), new CustomScanner()),
            getRootMenu()));
    rootMenu.addMenuItem(
        new MenuItem("6 - Back\n0 - Exit", () -> System.out.println("Back"), getRootMenu()));
    return rootMenu;
  }

  private Menu createSortStaleBookMenu() {
    Menu rootMenu = new Menu();
    rootMenu.setName(SORT_STALE_BOOKS);
    rootMenu.addMenuItem(
        new MenuItem("1 - Sorting by receipt date", new SortOldBookByDateAction(), getRootMenu()));
    rootMenu.addMenuItem(
        new MenuItem("2 - Sorting by price", new SortOldBookByPriceAction(), getRootMenu()));
    rootMenu.addMenuItem(
        new MenuItem("3 - Back\n0 - Exit", () -> System.out.println("Back"), getRootMenu()));
    return rootMenu;
  }

  private Menu createOrderMenu() {
    Menu rootMenu = new Menu();
    rootMenu.setName(ORDER_MENU);
    rootMenu.addMenuItem(
        new MenuItem(
            "1 - View all orders",
            () -> System.out.println("Select the sorting type"),
            createSortOrderMenu()));
    rootMenu.addMenuItem(
        new MenuItem(
            "2 - View completed orders",
            () -> System.out.println("Select the sorting type"),
            createSortCompletedOrderMenu()));
    rootMenu.addMenuItem(
        new MenuItem(
            "3 - Add order",
            new AddOrderAction(new CheckOfData(), new CustomScanner()),
            getRootMenu()));
    rootMenu.addMenuItem(
        new MenuItem(
            "4 - Close the order",
            new CloseOrderAction(new CheckOfData(), new CustomScanner()),
            getRootMenu()));
    rootMenu.addMenuItem(
        new MenuItem(
            "5 - Cancel the order",
            new CancelOrderAction(new CheckOfData(), new CustomScanner()),
            getRootMenu()));
    rootMenu.addMenuItem(
        new MenuItem(
            "6 - Amount of earned funds",
            new AmountEarnedFundsAction(new CustomScanner()),
            getRootMenu()));
    rootMenu.addMenuItem(
        new MenuItem(
            "7 - Number of completed orders",
            new CompletedOrderAction(new CustomScanner()),
            getRootMenu()));
    rootMenu.addMenuItem(
        new MenuItem("8 - Back\n0 - Exit", () -> System.out.println("Back"), getRootMenu()));
    return rootMenu;
  }

  private Menu createSortOrderMenu() {
    Menu rootMenu = new Menu();
    rootMenu.setName(SORT_ORDERS);
    rootMenu.addMenuItem(
        new MenuItem(
            "1 - Sorting by execution date", new SortOrderByExecurionDateAction(), getRootMenu()));
    rootMenu.addMenuItem(
        new MenuItem("2 - Sorting by price", new SortOrderByPriceAction(), getRootMenu()));
    rootMenu.addMenuItem(
        new MenuItem("3 - Sorting by status", new SortOrderByStatusAction(), getRootMenu()));
    rootMenu.addMenuItem(
        new MenuItem(
            "4 - Order information",
            new OrderInfoAction(new CheckOfData(), new CustomScanner()),
            getRootMenu()));
    rootMenu.addMenuItem(
        new MenuItem("5 - Back\n0 - Exit", () -> System.out.println("Back"), getRootMenu()));
    return rootMenu;
  }

  private Menu createSortCompletedOrderMenu() {
    Menu rootMenu = new Menu();
    rootMenu.setName(SORT_COMPLETED_ORDER);
    rootMenu.addMenuItem(
        new MenuItem(
            "1 - Sorting by execution date",
            new SortCompletedOrderByExecutionDateAction(new CustomScanner()),
            getRootMenu()));
    rootMenu.addMenuItem(
        new MenuItem(
            "2 - Sorting by price",
            new SortCompletedOrderByPriceAction(new CustomScanner()),
            getRootMenu()));
    rootMenu.addMenuItem(
        new MenuItem("3 - Back\n0 - Exit", () -> System.out.println("Back"), getRootMenu()));
    return rootMenu;
  }

  private Menu createRequestsMenu() {
    Menu rootMenu = new Menu();
    rootMenu.setName(REQUEST_MENU);
    rootMenu.addMenuItem(
        new MenuItem(
            "1 - View all requests",
            () -> System.out.println("Select the sorting type"),
            createSortRequestMenu()));
    rootMenu.addMenuItem(
        new MenuItem("2 - Add request", new AddRequestAction(new CustomScanner()), getRootMenu()));
    rootMenu.addMenuItem(
        new MenuItem("3 - Back\n0 - Exit", () -> System.out.println("Back"), getRootMenu()));
    return rootMenu;
  }

  private Menu createSortRequestMenu() {
    Menu rootMenu = new Menu();
    rootMenu.setName(SORT_REQUEST_MENU);
    rootMenu.addMenuItem(
        new MenuItem("1 - Sorting by count", new SortRequestByCountAction(), getRootMenu()));
    rootMenu.addMenuItem(
        new MenuItem(
            "2 - Sorting by alphabetically", new SortRequestByTitleAction(), getRootMenu()));
    rootMenu.addMenuItem(
        new MenuItem("3 - Back\n0 - Exit", () -> System.out.println("Back"), getRootMenu()));
    return rootMenu;
  }
}
package task4.UI;

import static task4.util.Constant.BOOK_MENU;
import static task4.util.Constant.ORDER_MENU;
import static task4.util.Constant.REQUEST_MENU;
import static task4.util.Constant.ROOT_MENU;
import static task4.util.Constant.SORT_BOOKS;
import static task4.util.Constant.SORT_COMPLETED_ORDER;
import static task4.util.Constant.SORT_ORDERS;
import static task4.util.Constant.SORT_REQUEST_MENU;
import static task4.util.Constant.SORT_STALE_BOOKS;

import task4.UI.action.Manager;
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
import task4.UI.action.orderAction.sortorder.SortOrderByExecutionDateAction;
import task4.UI.action.orderAction.sortorder.SortOrderByPriceAction;
import task4.UI.action.orderAction.sortorder.SortOrderByStatusAction;
import task4.UI.action.request.AddRequestAction;
import task4.UI.action.request.sortrequest.SortRequestByCountAction;
import task4.UI.action.request.sortrequest.SortRequestByTitleAction;

public class Builder {

  private static Builder instance;
  private Menu rootMenu;
  private Manager manager;

  private Builder(Manager manager) {
    this.manager = manager;
  }

  public static Builder getInstance(Manager manager) {
    if (instance == null) {
      instance = new Builder(manager);
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
    rootMenu.addMenuItem(new MenuItem("3 - Add book", new AddBookAction(manager), getRootMenu()));
    rootMenu.addMenuItem(
        new MenuItem("4 - Remove book", new RemoveBookAction(manager), getRootMenu()));
    rootMenu.addMenuItem(
        new MenuItem("5 - Back\n0 - Exit", () -> System.out.println("Back"), getRootMenu()));
    return rootMenu;
  }

  private Menu createSortBookMenu() {
    Menu rootMenu = new Menu();
    rootMenu.setName(SORT_BOOKS);
    rootMenu.addMenuItem(
        new MenuItem(
            "1 - Sorting by alphabetically", new SortBookByTitleAction(manager), getRootMenu()));
    rootMenu.addMenuItem(
        new MenuItem(
            "2 - Sorting by publication date",
            new SortBookByPublicationAction(manager),
            getRootMenu()));
    rootMenu.addMenuItem(
        new MenuItem("3 - Sorting by price", new SortBookByPriceAction(manager), getRootMenu()));
    rootMenu.addMenuItem(
        new MenuItem(
            "4 - Sorting by availability",
            new SortBookByAvailabilityAction(manager),
            getRootMenu()));
    rootMenu.addMenuItem(
        new MenuItem("5 - Book information", new BookInfoAction(manager), getRootMenu()));
    rootMenu.addMenuItem(
        new MenuItem("6 - Back\n0 - Exit", () -> System.out.println("Back"), getRootMenu()));
    return rootMenu;
  }

  private Menu createSortStaleBookMenu() {
    Menu rootMenu = new Menu();
    rootMenu.setName(SORT_STALE_BOOKS);
    rootMenu.addMenuItem(
        new MenuItem(
            "1 - Sorting by receipt date", new SortOldBookByDateAction(manager), getRootMenu()));
    rootMenu.addMenuItem(
        new MenuItem("2 - Sorting by price", new SortOldBookByPriceAction(manager), getRootMenu()));
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
    rootMenu.addMenuItem(new MenuItem("3 - Add order", new AddOrderAction(manager), getRootMenu()));
    rootMenu.addMenuItem(
        new MenuItem("4 - Close the order", new CloseOrderAction(manager), getRootMenu()));
    rootMenu.addMenuItem(
        new MenuItem("5 - Cancel the order", new CancelOrderAction(manager), getRootMenu()));
    rootMenu.addMenuItem(
        new MenuItem(
            "6 - Amount of earned funds", new AmountEarnedFundsAction(manager), getRootMenu()));
    rootMenu.addMenuItem(
        new MenuItem(
            "7 - Number of completed orders", new CompletedOrderAction(manager), getRootMenu()));
    rootMenu.addMenuItem(
        new MenuItem("8 - Back\n0 - Exit", () -> System.out.println("Back"), getRootMenu()));
    return rootMenu;
  }

  private Menu createSortOrderMenu() {
    Menu rootMenu = new Menu();
    rootMenu.setName(SORT_ORDERS);
    rootMenu.addMenuItem(
        new MenuItem(
            "1 - Sorting by execution date",
            new SortOrderByExecutionDateAction(manager),
            getRootMenu()));
    rootMenu.addMenuItem(
        new MenuItem("2 - Sorting by price", new SortOrderByPriceAction(manager), getRootMenu()));
    rootMenu.addMenuItem(
        new MenuItem("3 - Sorting by status", new SortOrderByStatusAction(manager), getRootMenu()));
    rootMenu.addMenuItem(
        new MenuItem("4 - Order information", new OrderInfoAction(manager), getRootMenu()));
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
            new SortCompletedOrderByExecutionDateAction(manager),
            getRootMenu()));
    rootMenu.addMenuItem(
        new MenuItem(
            "2 - Sorting by price", new SortCompletedOrderByPriceAction(manager), getRootMenu()));
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
        new MenuItem("2 - Add request", new AddRequestAction(manager), getRootMenu()));
    rootMenu.addMenuItem(
        new MenuItem("3 - Back\n0 - Exit", () -> System.out.println("Back"), getRootMenu()));
    return rootMenu;
  }

  private Menu createSortRequestMenu() {
    Menu rootMenu = new Menu();
    rootMenu.setName(SORT_REQUEST_MENU);
    rootMenu.addMenuItem(
        new MenuItem("1 - Sorting by count", new SortRequestByCountAction(manager), getRootMenu()));
    rootMenu.addMenuItem(
        new MenuItem(
            "2 - Sorting by alphabetically", new SortRequestByTitleAction(manager), getRootMenu()));
    rootMenu.addMenuItem(
        new MenuItem("3 - Back\n0 - Exit", () -> System.out.println("Back"), getRootMenu()));
    return rootMenu;
  }
}

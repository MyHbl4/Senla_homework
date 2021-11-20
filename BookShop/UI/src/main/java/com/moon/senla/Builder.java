package com.moon.senla;

import com.moon.senla.action.Manager;
import com.moon.senla.action.bookAction.AddBookAction;
import com.moon.senla.action.bookAction.BookInfoAction;
import com.moon.senla.action.bookAction.RemoveBookAction;
import com.moon.senla.action.bookAction.sortbook.SortBookByAvailabilityAction;
import com.moon.senla.action.bookAction.sortbook.SortBookByPriceAction;
import com.moon.senla.action.bookAction.sortbook.SortBookByPublicationAction;
import com.moon.senla.action.bookAction.sortbook.SortBookByTitleAction;
import com.moon.senla.action.bookAction.sortbook.SortOldBookByDateAction;
import com.moon.senla.action.bookAction.sortbook.SortOldBookByPriceAction;
import com.moon.senla.action.orderAction.AddOrderAction;
import com.moon.senla.action.orderAction.AmountEarnedFundsAction;
import com.moon.senla.action.orderAction.CancelOrderAction;
import com.moon.senla.action.orderAction.CloseOrderAction;
import com.moon.senla.action.orderAction.CompletedOrderAction;
import com.moon.senla.action.orderAction.OrderInfoAction;
import com.moon.senla.action.orderAction.sortorder.SortCompletedOrderByExecutionDateAction;
import com.moon.senla.action.orderAction.sortorder.SortCompletedOrderByPriceAction;
import com.moon.senla.action.orderAction.sortorder.SortOrderByExecutionDateAction;
import com.moon.senla.action.orderAction.sortorder.SortOrderByPriceAction;
import com.moon.senla.action.orderAction.sortorder.SortOrderByStatusAction;
import com.moon.senla.action.request.AddRequestAction;
import com.moon.senla.action.request.sortrequest.SortRequestByCountAction;
import com.moon.senla.action.request.sortrequest.SortRequestByTitleAction;
import com.moon.senla.action.util.Constant;

public class Builder {

  private static Builder instance;
  private final Manager manager;
  private Menu rootMenu;

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
    rootMenu.setName(Constant.ROOT_MENU);
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
    rootMenu.setName(Constant.BOOK_MENU);
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
    rootMenu.setName(Constant.SORT_BOOKS);
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
    rootMenu.setName(Constant.SORT_STALE_BOOKS);
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
    rootMenu.setName(Constant.ORDER_MENU);
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
    rootMenu.setName(Constant.SORT_ORDERS);
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
    rootMenu.setName(Constant.SORT_COMPLETED_ORDER);
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
    rootMenu.setName(Constant.REQUEST_MENU);
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
    rootMenu.setName(Constant.SORT_REQUEST_MENU);
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

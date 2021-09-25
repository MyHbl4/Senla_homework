package task4.UI;

import task4.UI.action.AddBook;
import task4.UI.action.AddOrder;
import task4.UI.action.AddRequest;
import task4.UI.action.AmountEarnedFunds;
import task4.UI.action.BookInfo;
import task4.UI.action.CancelOrder;
import task4.UI.action.CloseOrder;
import task4.UI.action.CompletedOrder;
import task4.UI.action.OrderInfo;
import task4.UI.action.RemoveBook;
import task4.UI.action.SortBookByAvailability;
import task4.UI.action.SortBookByPrice;
import task4.UI.action.SortBookByPublication;
import task4.UI.action.SortBookByTitle;
import task4.UI.action.SortCompletedOrderByExecutionDate;
import task4.UI.action.SortCompletedOrderByPrice;
import task4.UI.action.SortOldBookByDate;
import task4.UI.action.SortOldBookByPrice;
import task4.UI.action.SortOrderByExecurionDate;
import task4.UI.action.SortOrderByPrice;
import task4.UI.action.SortOrderByStatus;
import task4.UI.action.SortRequestByCount;
import task4.UI.action.SortRequestByTitle;

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
    rootMenu.setName("\n== Root Menu ==");
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
    var rootMenu = new Menu();
    rootMenu.setName("\n== Book Menu ==");
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
    rootMenu.addMenuItem(new MenuItem("3 - Add book", new AddBook(), getRootMenu()));
    rootMenu.addMenuItem(new MenuItem("4 - Remove book", new RemoveBook(), getRootMenu()));
    rootMenu.addMenuItem(
        new MenuItem("5 - Back\n0 - Exit", () -> System.out.println("Back"), getRootMenu()));
    return rootMenu;
  }

  private Menu createSortBookMenu() {
    var rootMenu = new Menu();
    rootMenu.setName("\n== All Books ==");
    rootMenu.addMenuItem(
        new MenuItem("1 - Sorting by alphabetically", new SortBookByTitle(), getRootMenu()));
    rootMenu.addMenuItem(
        new MenuItem(
            "2 - Sorting by publication date", new SortBookByPublication(), getRootMenu()));
    rootMenu.addMenuItem(
        new MenuItem("3 - Sorting by price", new SortBookByPrice(), getRootMenu()));
    rootMenu.addMenuItem(
        new MenuItem("4 - Sorting by availability", new SortBookByAvailability(), getRootMenu()));
    rootMenu.addMenuItem(new MenuItem("5 - Book information", new BookInfo(), getRootMenu()));
    rootMenu.addMenuItem(
        new MenuItem("6 - Back\n0 - Exit", () -> System.out.println("Back"), getRootMenu()));
    return rootMenu;
  }

  private Menu createSortStaleBookMenu() {
    var rootMenu = new Menu();
    rootMenu.setName("\n== Sort Stale Book Menu ==");
    rootMenu.addMenuItem(
        new MenuItem("1 - Sorting by receipt date", new SortOldBookByDate(), getRootMenu()));
    rootMenu.addMenuItem(
        new MenuItem("2 - Sorting by price", () -> new SortOldBookByPrice(), getRootMenu()));
    rootMenu.addMenuItem(
        new MenuItem("3 - Back\n0 - Exit", () -> System.out.println("Back"), getRootMenu()));
    return rootMenu;
  }

  private Menu createOrderMenu() {
    var rootMenu = new Menu();
    rootMenu.setName("\n== Order Menu ==");
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
    rootMenu.addMenuItem(new MenuItem("3 - Add order", new AddOrder(), getRootMenu()));
    rootMenu.addMenuItem(new MenuItem("4 - Close the order", new CloseOrder(), getRootMenu()));
    rootMenu.addMenuItem(new MenuItem("5 - Cancel the order", new CancelOrder(), getRootMenu()));
    rootMenu.addMenuItem(
        new MenuItem("6 - Amount of earned funds", new AmountEarnedFunds(), getRootMenu()));
    rootMenu.addMenuItem(
        new MenuItem("7 - Number of completed orders", new CompletedOrder(), getRootMenu()));
    rootMenu.addMenuItem(
        new MenuItem("8 - Back\n0 - Exit", () -> System.out.println("Back"), getRootMenu()));
    return rootMenu;
  }

  private Menu createSortOrderMenu() {
    var rootMenu = new Menu();
    rootMenu.setName("\n== Sort Order Menu ==");
    rootMenu.addMenuItem(
        new MenuItem(
            "1 - Sorting by execution date", new SortOrderByExecurionDate(), getRootMenu()));
    rootMenu.addMenuItem(
        new MenuItem("2 - Sorting by price", new SortOrderByPrice(), getRootMenu()));
    rootMenu.addMenuItem(
        new MenuItem("3 - Sorting by status", new SortOrderByStatus(), getRootMenu()));
    rootMenu.addMenuItem(new MenuItem("4 - Order information", new OrderInfo(), getRootMenu()));
    rootMenu.addMenuItem(
        new MenuItem("5 - Back\n0 - Exit", () -> System.out.println("Back"), getRootMenu()));
    return rootMenu;
  }

  private Menu createSortCompletedOrderMenu() {
    var rootMenu = new Menu();
    rootMenu.setName("\n== Sort Order Menu ==");
    rootMenu.addMenuItem(
        new MenuItem(
            "1 - Sorting by execution date",
            new SortCompletedOrderByExecutionDate(),
            getRootMenu()));
    rootMenu.addMenuItem(
        new MenuItem("2 - Sorting by price", new SortCompletedOrderByPrice(), getRootMenu()));
    rootMenu.addMenuItem(
        new MenuItem("3 - Back\n0 - Exit", () -> System.out.println("Back"), getRootMenu()));
    return rootMenu;
  }

  private Menu createRequestsMenu() {
    var rootMenu = new Menu();
    rootMenu.setName("\n== Requests Menu ==");
    rootMenu.addMenuItem(
        new MenuItem(
            "1 - View all requests",
            () -> System.out.println("Select the sorting type"),
            createSortRequestMenu()));
    rootMenu.addMenuItem(new MenuItem("2 - Add request", new AddRequest(), getRootMenu()));
    rootMenu.addMenuItem(
        new MenuItem("3 - Back\n0 - Exit", () -> System.out.println("Back"), getRootMenu()));
    return rootMenu;
  }

  private Menu createSortRequestMenu() {
    var rootMenu = new Menu();
    rootMenu.setName("\n== Sort Request Menu ==");
    rootMenu.addMenuItem(
        new MenuItem("1 - Sorting by count", new SortRequestByCount(), getRootMenu()));
    rootMenu.addMenuItem(
        new MenuItem("2 - Sorting by alphabetically", new SortRequestByTitle(), getRootMenu()));
    rootMenu.addMenuItem(
        new MenuItem("3 - Back\n0 - Exit", () -> System.out.println("Back"), getRootMenu()));
    return rootMenu;
  }
}

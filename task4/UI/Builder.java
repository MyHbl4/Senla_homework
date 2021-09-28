package task4.UI;

import task4.UI.action.book.AddBook;
import task4.UI.action.order.AddOrder;
import task4.UI.action.request.AddRequest;
import task4.UI.action.order.AmountEarnedFunds;
import task4.UI.action.book.BookInfo;
import task4.UI.action.order.CancelOrder;
import task4.UI.action.order.CloseOrder;
import task4.UI.action.order.CompletedOrder;
import task4.UI.action.order.OrderInfo;
import task4.UI.action.book.RemoveBook;
import task4.UI.action.book.sortbook.SortBookByAvailability;
import task4.UI.action.book.sortbook.SortBookByPrice;
import task4.UI.action.book.sortbook.SortBookByPublication;
import task4.UI.action.book.sortbook.SortBookByTitle;
import task4.UI.action.order.sortorder.SortCompletedOrderByExecutionDate;
import task4.UI.action.order.sortorder.SortCompletedOrderByPrice;
import task4.UI.action.book.sortbook.SortOldBookByDate;
import task4.UI.action.book.sortbook.SortOldBookByPrice;
import task4.UI.action.order.sortorder.SortOrderByExecurionDate;
import task4.UI.action.order.sortorder.SortOrderByPrice;
import task4.UI.action.order.sortorder.SortOrderByStatus;
import task4.UI.action.request.sortrequest.SortRequestByCount;
import task4.UI.action.request.sortrequest.SortRequestByTitle;
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
    rootMenu.addMenuItem(new MenuItem("3 - Add book", new AddBook(new CustomScanner()), getRootMenu()));
    rootMenu.addMenuItem(new MenuItem("4 - Remove book", new RemoveBook(new CheckOfData(), new CustomScanner()), getRootMenu()));
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
    rootMenu.addMenuItem(new MenuItem("5 - Book information", new BookInfo(new CheckOfData(), new CustomScanner()), getRootMenu()));
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
    rootMenu.addMenuItem(new MenuItem("3 - Add order", new AddOrder(new CheckOfData(), new CustomScanner()), getRootMenu()));
    rootMenu.addMenuItem(new MenuItem("4 - Close the order", new CloseOrder(new CheckOfData(), new CustomScanner()), getRootMenu()));
    rootMenu.addMenuItem(new MenuItem("5 - Cancel the order", new CancelOrder(new CheckOfData(), new CustomScanner()), getRootMenu()));
    rootMenu.addMenuItem(
        new MenuItem("6 - Amount of earned funds", new AmountEarnedFunds(new CustomScanner()), getRootMenu()));
    rootMenu.addMenuItem(
        new MenuItem("7 - Number of completed orders", new CompletedOrder(new CustomScanner()), getRootMenu()));
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
    rootMenu.addMenuItem(new MenuItem("4 - Order information", new OrderInfo(new CheckOfData(), new CustomScanner()), getRootMenu()));
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
            new SortCompletedOrderByExecutionDate(new CustomScanner()),
            getRootMenu()));
    rootMenu.addMenuItem(
        new MenuItem("2 - Sorting by price", new SortCompletedOrderByPrice(new CustomScanner()), getRootMenu()));
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
    rootMenu.addMenuItem(new MenuItem("2 - Add request", new AddRequest(new CustomScanner()), getRootMenu()));
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

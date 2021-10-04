package task4.model;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import task4.enums.OrderStatus;

public class Order extends Identity {
  private long id = createOrderid();
  private String customerName;
  private List<Book> books;
  private OrderStatus orderStatus = OrderStatus.NEW;
  private LocalDate execution = LocalDate.of(0000,01,01);
  private int price = getPrice();


  public Order(long id, String customerName, List<Book> books, OrderStatus orderStatus,
      LocalDate execution) {
    this.id = id;
    this.customerName = customerName;
    this.books = books;
    this.orderStatus = orderStatus;
    this.execution = execution;
  }

  public Order(long id, String customerName, List<Book> books) {
    this.id = id;
    this.customerName = customerName;
    this.books = books;
  }

  public Order(String customerName, List<Book> books) {
    this.customerName = customerName;
    this.books = books;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getCustomerName() {
    return customerName;
  }

  public void setCustomerName(String customerName) {
    this.customerName = customerName;
  }

  public List<Book> getBooks() {
    return books;
  }

  public void setBooks(List<Book> books) {
    this.books = books;
  }

  public OrderStatus getOrderStatus() {
    return orderStatus;
  }

  public void setOrderStatus(OrderStatus orderStatus) {
    this.orderStatus = orderStatus;
  }

  public LocalDate getExecution() {
    return execution;
  }

  public void setExecution(LocalDate execution) {
    this.execution = execution;
  }

  public void setOrderStatusNew() {
    this.orderStatus = OrderStatus.NEW;
  }

  public void setOrderStatusCompleate() {
    this.orderStatus = OrderStatus.COMPLETED;
  }

  public void setOrderStatusCanceled() {
    this.orderStatus = OrderStatus.CANCELED;
  }

  public int getPrice() {
    int prices = 0;
    if (books != null) {
      for (Book book : books) {
        prices += book.getPrice();
      }
    }
    return prices;
  }

  public void showBooks() {
    System.out.print("Books in order: ");
    for (Book book : books) {
      System.out.print('\'' + book.getTitle() + "' ");
    }
    System.out.println();
  }

  public String getBooksId() {
    String strBooks = "";
    for (int i = 0; i < books.size(); i++) {
      if (i + 1 == books.size()) {
        strBooks += books.get(i).getId();
      } else {
        strBooks += books.get(i).getId() + ",";
      }
    }
    return strBooks;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Order order = (Order) o;
    return getId() == order.getId()
        && getPrice() == order.getPrice()
        && Objects.equals(getCustomerName(), order.getCustomerName())
        && Objects.equals(getBooks(), order.getBooks())
        && getOrderStatus() == order.getOrderStatus()
        && Objects.equals(getExecution(), order.getExecution());
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        getId(), getCustomerName(), getBooks(), getOrderStatus(), getExecution(), getPrice());
  }

  @Override
  public String toString() {
    return "Order - "
        + "ID: "
        + id
        + ", Customer name: '"
        + customerName
        + "', Books: "
        + books
        + ", Order status: "
        + orderStatus
        + ", Execution: "
        + execution
        + ", Price: "
        + getPrice();
  }
}

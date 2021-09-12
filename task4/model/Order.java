package task4.model;

import java.util.Arrays;

public class Order {
  private int id;
  private int buyerId;
  private int[] booksId;
  private OrderStatus orderStatus = OrderStatus.NEW;

  public Order(int id, int buyerId, int[] booksId) {
    this.id = id;
    this.buyerId = buyerId;
    this.booksId = booksId;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getBuyerId() {
    return buyerId;
  }

  public void setBuyerId(int buyerId) {
    this.buyerId = buyerId;
  }

  public int[] getBooksId() {
    return booksId;
  }

  public void setBooksId(int[] booksId) {
    this.booksId = booksId;
  }

  public OrderStatus getOrderStatus() {
    return orderStatus;
  }

  public void setOrderStatus(OrderStatus orderStatus) {
    this.orderStatus = orderStatus;
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

  @Override
  public String toString() {
    return "Order{"
        + "id="
        + id
        + ", buyerId="
        + buyerId
        + ", booksId="
        + Arrays.toString(booksId)
        + ", orderStatus="
        + orderStatus
        + '}';
  }
}

package task4.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import task4.util.BookArrayList;

public class Order {
  private int id;
  private int buyerId;
  private Object[] books;
  private OrderStatus orderStatus = OrderStatus.NEW;
  private LocalDate execution = LocalDate.now();

  public Order(int id, int buyerId, Object[] books) {
    this.id = id;
    this.buyerId = buyerId;
    this.books = books;
  }

  public LocalDate getExecution() {
    return execution;
  }

  public void setExecution(LocalDate execution) {
    this.execution = execution;
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

  public Object[] getBooks() {
    return books;
  }

  public void setBooks(Object[] books) {
    this.books = books;
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

  public int getPrice() {
    int price = 0;
    for (int i = 0; i < books.length; i++) {
      Book book = (Book) books[i];
      price += book.getPrice();
    }
    return price;
  }

//  public List<Book> getBookInOrder(){
//    List<Book> listBook = new ArrayList<>();
//    for (Book book:books){
//      listBook.add((Book)book);
//    }
//    return listBook;
//  }

  public void showBooks() {
    System.out.print("Books in order: ");
    for (int i = 0; i < books.length; i++) {
      Book book = (Book) books[i];
      if (i + 1 != books.length) {
        System.out.print(book.getTitle() + ", ");
      } else {
        System.out.println(book.getTitle());
      }
    }
  }

  public BookArrayList getBooksInOrder() {
    BookArrayList books2 = new BookArrayList();
    for (int i=0;i<books.length;i++){
      books2.add((Book)books[i]);
    }
    return books2;
  }

  @Override
  public String toString() {
    return "Order{"
        + "id="
        + id
        + ", buyerId="
        + buyerId
        + ", orderStatus="
        + orderStatus
        + ", execution="
        + execution
        + ", price="
        + getPrice()
        + '}';
  }
}

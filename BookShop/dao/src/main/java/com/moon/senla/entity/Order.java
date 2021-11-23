package com.moon.senla.entity;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.moon.senla.enums.OrderStatus;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "orders")
public class Order {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private long id;

  @Column(name = "customer")
  private String customerName;

  @JsonIgnoreProperties("orders")
  @JsonIdentityReference(alwaysAsId = true)
  @ManyToMany(
      cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.DETACH, CascadeType.MERGE},
      fetch = FetchType.EAGER)
  @JoinTable(
      name = "order_books",
      joinColumns = @JoinColumn(name = "order_id", referencedColumnName = "id"),
      inverseJoinColumns = @JoinColumn(name = "book_id", referencedColumnName = "id"))
  private List<com.moon.senla.entity.Book> books;

  @Column(name = "price")
  private int price;

  @Column(name = "status")
  @Enumerated(EnumType.STRING)
  private OrderStatus orderStatus = OrderStatus.NEW;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
  @JsonDeserialize(using = LocalDateDeserializer.class)
  @JsonSerialize(using = LocalDateSerializer.class)
  @Column(name = "execution")
  private LocalDate execution = LocalDate.of(0001, 01, 01);

  public Order() {}

  public Order(long id, String customerName, OrderStatus orderStatus, LocalDate execution) {
    this.id = id;
    this.customerName = customerName;
    this.orderStatus = orderStatus;
    this.execution = execution;
  }

  public Order(
      long id,
      String customerName,
      List<com.moon.senla.entity.Book> books,
      OrderStatus orderStatus,
      LocalDate execution) {
    this.id = id;
    this.customerName = customerName;
    this.books = books;
    this.orderStatus = orderStatus;
    this.execution = execution;
  }

  public Order(long id, String customerName, List<com.moon.senla.entity.Book> books) {
    this.id = id;
    this.customerName = customerName;
    this.books = books;
  }

  public Order(String customerName, List<com.moon.senla.entity.Book> books) {
    this.customerName = customerName;
    this.books = books;
    this.price = getPrice();
  }

  public Order(String customerName) {
    this.customerName = customerName;
  }

  public Order(String customerName, List<com.moon.senla.entity.Book> books, int price) {
    this.customerName = customerName;
    this.books = books;
    this.price = price;
  }

  public List<com.moon.senla.entity.Book> getBooks() {
    return books;
  }

  public void setBooks(List<com.moon.senla.entity.Book> books) {
    this.books = books;
  }

  public void addBook(com.moon.senla.entity.Book book) {
    books.add(book);
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
    this.execution = LocalDate.now();
  }

  public void setOrderStatusCanceled() {
    this.orderStatus = OrderStatus.CANCELED;
  }

  public int getPrice() {
    int prices = 0;
    if (books != null) {
      for (com.moon.senla.entity.Book book : books) {
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

  @Transient
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
        + ", Price: "
        + price
        + ", Books: "
        + books
        + ", Order status: "
        + orderStatus
        + ", Execution: "
        + execution;
  }
}

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
import com.moon.senla.enums.Availability;
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
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "books")
public class Book {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private long id;

  @Column(name = "title")
  private String title;

  @Column(name = "author")
  private String author;

  @Column(name = "price")
  private int price;

  @Enumerated(EnumType.STRING)
  @Column(name = "availability")
  private Availability availability = Availability.IN_STOCK;

  @Column(name = "publication")
  private int publication;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
  @JsonDeserialize(using = LocalDateDeserializer.class)
  @JsonSerialize(using = LocalDateSerializer.class)
  @Column(name = "delivery_date")
  private LocalDate deliveryDate = LocalDate.now();

  @JsonIgnoreProperties("books")
  @JsonIdentityReference(alwaysAsId = true)
  @ManyToMany(
      cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.DETACH, CascadeType.MERGE},
      fetch = FetchType.LAZY)
  @JoinTable(
      name = "order_books",
      joinColumns = @JoinColumn(name = "book_id", referencedColumnName = "id"),
      inverseJoinColumns = @JoinColumn(name = "order_id", referencedColumnName = "id"))
  private List<Order> orderList;

  @JsonIgnoreProperties("book")
  @OneToOne(mappedBy = "book", fetch = FetchType.LAZY)
  private Request request;

  public Book(
      long id,
      String title,
      String author,
      int price,
      int publication,
      Availability availability,
      LocalDate deliveryDate) {
    this.id = id;
    this.title = title;
    this.author = author;
    this.price = price;
    this.availability = availability;
    this.publication = publication;
    this.deliveryDate = deliveryDate;
  }

  public Book(String title, String author, int price, int publication) {
    this.title = title;
    this.author = author;
    this.price = price;
    this.publication = publication;
  }

  public Book(
      String title,
      String author,
      int price,
      Availability availability,
      int publication,
      LocalDate deliveryDate) {
    this.title = title;
    this.author = author;
    this.price = price;
    this.availability = availability;
    this.publication = publication;
    this.deliveryDate = deliveryDate;
  }

  public List<Order> getOrderList() {
    return orderList;
  }

  public void setOrderList(List<Order> orderList) {
    this.orderList = orderList;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public int getPrice() {
    return price;
  }

  public void setPrice(int price) {
    this.price = price;
  }

  public Availability getAvailability() {
    return availability;
  }

  public void setAvailability(Availability availability) {
    this.availability = availability;
  }

  public int getPublication() {
    return publication;
  }

  public void setPublication(int publication) {
    this.publication = publication;
  }

  public LocalDate getDeliveryDate() {
    return deliveryDate;
  }

  public void setDeliveryDate(LocalDate deliveryDate) {
    this.deliveryDate = deliveryDate;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Book book = (Book) o;
    return getId() == book.getId()
        && getPrice() == book.getPrice()
        && getPublication() == book.getPublication()
        && Objects.equals(getTitle(), book.getTitle())
        && Objects.equals(getAuthor(), book.getAuthor())
        && getAvailability() == book.getAvailability()
        && Objects.equals(getDeliveryDate(), book.getDeliveryDate());
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        getId(),
        getTitle(),
        getAuthor(),
        getPrice(),
        getAvailability(),
        getPublication(),
        getDeliveryDate());
  }

  @Override
  public String toString() {
    return "Book - "
        + "ID: "
        + id
        + ", Title: '"
        + title
        + '\''
        + ", Author: '"
        + author
        + '\''
        + ", Publication: "
        + publication
        + ", Price: "
        + price
        + ", Availability: "
        + availability
        + ", Delivery date: "
        + deliveryDate;
  }
}

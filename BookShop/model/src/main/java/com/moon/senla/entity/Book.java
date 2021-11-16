package com.moon.senla.entity;

import java.time.LocalDate;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.moon.senla.enums.Availability;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "book")
public class Book {
  @Id
  @Column
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  @Column(name = "title")
  private String title;

  @Column(name = "author")
  private String author;

  @Column(name = "price")
  private int price;

  @Column(name = "availability")
  private Availability availability = Availability.IN_STOCK;

  @Column(name = "publication")
  private int publication;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
  @JsonDeserialize(using = LocalDateDeserializer.class)
  @JsonSerialize(using = LocalDateSerializer.class)
  @Column(name = "delivery_date")
  private LocalDate deliveryDate = LocalDate.now();

  public Book() { }

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
        + ", Price: "
        + price
        + ", Availability: "
        + availability
        + ", Publication: "
        + publication
        + ", Delivery date: "
        + deliveryDate;
  }
}

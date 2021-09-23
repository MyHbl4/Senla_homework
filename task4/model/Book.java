package task4.model;

import java.time.LocalDate;
import java.util.Objects;

public class Book extends Identity {
  private long id = createBookId();
  private String title;
  private String author;
  private int price;
  private Availability availability = Availability.IN_STOCK;
  private int publication;
  private LocalDate deliveryDate = LocalDate.now();

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
    return "Book{"
        + "id="
        + id
        + ", title='"
        + title
        + '\''
        + ", author='"
        + author
        + '\''
        + ", price="
        + price
        + ", availability="
        + availability
        + ", publication="
        + publication
        + ", deliveryDate="
        + deliveryDate
        + "} ";
  }
}

package task4.model;

import java.time.LocalDate;

public class Book {
  private int id;
  private String title;
  private String author;
  private int price;
  private Genre genre;
  private Availability availability = Availability.IN_STOCK;
  private int publication;
  private LocalDate deliveryDate = LocalDate.now();

  public Book(int id, String title, String author, int price, Genre genre, int publication) {
    this.id = id;
    this.title = title;
    this.author = author;
    this.price = price;
    this.genre = genre;
    this.publication = publication;
  }

  public Book(EnumBook title) {}

  public int getPublication() {
    return publication;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
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

  public Genre getGenre() {
    return genre;
  }

  public void setGenre(Genre genre) {
    this.genre = genre;
  }

  public Availability getAvailability() {
    return availability;
  }

  public void setAvailability(Availability availability) {
    this.availability = availability;
  }

  public void setBookStatusInStock() {
    this.availability = Availability.IN_STOCK;
  }

  public void setBookStatusOutOfStock() {
    this.availability = Availability.OUT_OF_STOCK;
  }

  public LocalDate getDeliveryDate() {
    return deliveryDate;
  }

  public void setDeliveryDate(LocalDate deliveryDate) {
    this.deliveryDate = deliveryDate;
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
        + ", published="
        + publication
        + ", price="
        + price
        + ", genre="
        + genre
        + ", availability="
        + availability
        + ", delivery date="
        + deliveryDate
        + '}';
  }
}

package com.moon.senla.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "requests")
public class Request {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private long id;

  @Column(name = "count")
  private int count = 1;

  @JsonIgnoreProperties({"orders", "request"})
  @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JoinColumn(name = "book_id")
  private long bookId;

  @JsonIgnoreProperties({"orders", "request"})
  @OneToOne (cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JoinColumn(name = "book_title")
  private String title = null;

  public Request() {}

  public Request(long bookId, String title) {
    this.bookId = bookId;
    this.title = title;
  }

  public Request(long id, int count, long bookId) {
    this.id = id;
    this.count = count;
    this.bookId = bookId;
  }

  public Request(long id, int count, long bookId, String title) {
    this.id = id;
    this.count = count;
    this.bookId = bookId;
    this.title = title;
  }

  public Request(String title) {
    this.title = title;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public long getBookId() {
    return bookId;
  }

  public void setBookId(long bookId) {
    this.bookId = bookId;
  }

  public int getCount() {
    return count;
  }

  public void setCount(int count) {
    this.count = count;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  @Override
  public String toString() {
    return "Request - "
        + "ID: "
        + id
        + ", Count: "
        + count
        + ", Book ID: "
        + bookId
        + ", Title: '"
        + title
        + "'";
  }
}

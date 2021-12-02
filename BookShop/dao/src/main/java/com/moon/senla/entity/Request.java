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
    private Book book;

    @Column(name = "title")
    private String title;

    public Request() {
    }

    public Request(Book book) {
        this.book = book;
        this.title = book.getTitle();
    }

    public String getTitle() {
        return book.getTitle();
    }

    @Override
    public String toString() {
        return "Request - "
                + "ID: "
                + id
                + ", Count: "
                + count
                + ", Title: '"
                + title
                + "'";
    }
}

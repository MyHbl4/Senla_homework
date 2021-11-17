package com.moon.senla.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "order_books")
public class OrderBooks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, insertable = true, updatable = true)
    private long id;

//    @JsonIgnoreProperties({"orders"})
//    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private int orderId;

//    @JsonIgnoreProperties({"books"})
//    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private int bookId;

    public OrderBooks() {
    }


    public OrderBooks(int orderId, int bookId) {
        this.orderId = orderId;
        this.bookId = bookId;
    }

    public OrderBooks(long id, int orderId, int bookId) {
        this.id = id;
        this.orderId = orderId;
        this.bookId = bookId;
    }
}

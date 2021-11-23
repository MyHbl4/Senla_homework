package com.moon.senla.action;

import com.moon.senla.service.BookService;
import com.moon.senla.service.OrderService;
import com.moon.senla.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BookStoreManager implements Manager {
  private BookService bookService;
  private OrderService orderService;
  private RequestService requestService;

  @Autowired
  public BookStoreManager(
      BookService bookService, OrderService orderService, RequestService requestService) {
    this.bookService = bookService;
    this.orderService = orderService;
    this.requestService = requestService;
  }

  public BookService getBookService() {
    return bookService;
  }

  public OrderService getOrderService() {
    return orderService;
  }

  public RequestService getRequestService() {
    return requestService;
  }
}

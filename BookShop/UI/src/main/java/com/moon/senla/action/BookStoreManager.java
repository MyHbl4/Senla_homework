package com.moon.senla.action;

import com.moon.senla.BookService;
import com.moon.senla.OrderService;
import com.moon.senla.RequestService;
import com.moon.senla.annotations.InjectByType;

public class BookStoreManager implements Manager {
  @InjectByType private BookService bookService;
  @InjectByType private OrderService orderService;
  @InjectByType private RequestService requestService;

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

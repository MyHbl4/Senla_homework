package com.moon.senla.action;

import com.moon.senla.BookService;
import com.moon.senla.OrderService;
import com.moon.senla.RequestService;

public interface Manager {

  BookService getBookService();

  OrderService getOrderService();

  RequestService getRequestService();
}

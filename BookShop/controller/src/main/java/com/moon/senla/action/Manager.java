package com.moon.senla.action;

import com.moon.senla.service.BookService;
import com.moon.senla.service.OrderService;
import com.moon.senla.service.RequestService;

public interface Manager {

  BookService getBookService();

  OrderService getOrderService();

  RequestService getRequestService();
}

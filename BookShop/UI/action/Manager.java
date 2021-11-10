package task4.UI.action;

import task4.service.BookService;
import task4.service.OrderService;
import task4.service.RequestService;

public interface Manager {

  BookService getBookService();

  OrderService getOrderService();

  RequestService getRequestService();
}

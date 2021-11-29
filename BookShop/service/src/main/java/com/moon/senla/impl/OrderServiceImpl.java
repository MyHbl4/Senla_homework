package com.moon.senla.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.moon.senla.BookRepository;
import com.moon.senla.OrderRepository;
import com.moon.senla.OrderService;
import com.moon.senla.RequestRepository;
import com.moon.senla.entity.Book;
import com.moon.senla.entity.Order;
import com.moon.senla.enums.Availability;
import com.moon.senla.enums.OrderStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderService {
  private static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);
  private OrderRepository orderRepository;
  private BookRepository bookRepository;
  private RequestRepository requestRepository;
  private OrderDao orderDAO;

  @Autowired
  public OrderServiceImpl(
      OrderRepository orderRepository,
      BookRepository bookRepository,
      RequestRepository requestRepository,
      OrderDao orderDAO) {
    this.orderRepository = orderRepository;
    this.bookRepository = bookRepository;
    this.requestRepository = requestRepository;
    this.orderDAO = orderDAO;
  }

  @Override
  public Order findOrderById(int id) {
    return orderRepository.findOrderById(id);
  }

  @Override
  public void addOrder(Order order) {
    try {
      orderDAO.create(order);
      orderDAO.createOrderBooks(order);
      if (checkBooksInOrder(order)) {
        closeOrder((int) order.getId());
        bookRepository.removeBooks(order.getBooks());
      } else {
        checkBooksForRequest(order);
      }
      logger.info(
          "Method completed - " + Thread.currentThread().getStackTrace()[1].getMethodName());
    } catch (Exception e) {
      logger.warn(
          "Failed to execute the method - "
              + Thread.currentThread().getStackTrace()[1].getMethodName(),
          e);
    }
  }

  @Override
  public void checkBooksForRequest(Order order) {
    try {
      for (int i = 0; i < order.getBooks().size(); i++) {
        if (order.getBooks().get(i).getTitle() != null) {
          for (int k = 0; k < bookRepository.getAll().size(); k++) {
            if (order.getBooks().get(i).getTitle().equals(bookRepository.getAll().get(k).getTitle())
                && bookRepository
                    .getAll()
                    .get(k)
                    .getAvailability()
                    .equals(Availability.OUT_OF_STOCK)) {
              requestRepository.addRequest(bookRepository.getAll().get(k).getId());
            }
          }
        }
      }
      logger.info(
          "Method completed - " + Thread.currentThread().getStackTrace()[1].getMethodName());
    } catch (Exception e) {
      logger.warn(
          "Failed to execute the method - "
              + Thread.currentThread().getStackTrace()[1].getMethodName(),
          e);
    }
  }

  @Override
  public boolean checkBooksInOrder(Order order) {
    boolean availability = false;
    try {
      availability = true;
      for (Book book : order.getBooks()) {
        if (book.getAvailability().equals(Availability.OUT_OF_STOCK)) {
          availability = false;
        }
      }
      logger.info(
          "Method completed - " + Thread.currentThread().getStackTrace()[1].getMethodName());
    } catch (Exception e) {
      logger.warn(
          "Failed to execute the method - "
              + Thread.currentThread().getStackTrace()[1].getMethodName(),
          e);
    }
    return availability;
  }

  @Override
  public void closeOrder(int id) {
    try {
      Order order = orderDAO.read(id);
      order.setOrderStatusCompleate();
      orderDAO.update(order);
      logger.info(
          "Method completed - " + Thread.currentThread().getStackTrace()[1].getMethodName());
    } catch (Exception e) {
      logger.warn(
          "Failed to execute the method - "
              + Thread.currentThread().getStackTrace()[1].getMethodName(),
          e);
    }
  }

  @Override
  public void cancelOrder(int id) {
    try {
      Order order = orderDAO.read(id);
      order.setOrderStatusCanceled();
      orderDAO.update(order);
      logger.info(
          "Method completed - " + Thread.currentThread().getStackTrace()[1].getMethodName());
    } catch (Exception e) {
      logger.warn(
          "Failed to execute the method - "
              + Thread.currentThread().getStackTrace()[1].getMethodName(),
          e);
    }
  }

  @Override
  public int getAllPriceOfSoldBooks(int months) {
    int price = 0;
    try {
      price = 0;
      for (Order order : getCompletedOrderList(months)) {
        price += order.getPrice();
      }
      logger.info(
          "Method completed - " + Thread.currentThread().getStackTrace()[1].getMethodName());
    } catch (Exception e) {
      logger.warn(
          "Failed to execute the method - "
              + Thread.currentThread().getStackTrace()[1].getMethodName(),
          e);
    }
    return price;
  }

  @Override
  public List<Order> getAll() {
    return orderRepository.getAll();
  }

  @Override
  public List<Order> getCompletedOrderList(int months) {
    List<Order> completedOrder = null;
    try {
      completedOrder = new ArrayList<>();
      for (Order order : orderRepository.getAll()) {
        if (order.getOrderStatus().equals(OrderStatus.COMPLETED)
            && order.getExecution().isAfter(LocalDate.now().minusMonths(months))) {
          completedOrder.add(order);
        }
      }
      logger.info(
          "Method completed - " + Thread.currentThread().getStackTrace()[1].getMethodName());
    } catch (Exception e) {
      logger.warn(
          "Failed to execute the method - "
              + Thread.currentThread().getStackTrace()[1].getMethodName(),
          e);
    }
    return completedOrder;
  }

  @Override
  public List<Order> sortCompletedOrderByExecutionDate(int months) {
    List<Order> sortOrders = null;
    try {
      sortOrders = getCompletedOrderList(months);
      sortOrders.sort((Comparator.comparing(Order::getExecution)));
      logger.info(
          "Method completed - " + Thread.currentThread().getStackTrace()[1].getMethodName());
    } catch (Exception e) {
      logger.warn(
          "Failed to execute the method - "
              + Thread.currentThread().getStackTrace()[1].getMethodName(),
          e);
    }
    return sortOrders;
  }

  @Override
  public List<Order> sortCompletedOrderByPrice(int months) {
    List<Order> sortOrders = null;
    try {
      sortOrders = getCompletedOrderList(months);
      sortOrders.sort((Comparator.comparingInt(Order::getPrice)));
      logger.info(
          "Method completed - " + Thread.currentThread().getStackTrace()[1].getMethodName());
    } catch (Exception e) {
      logger.warn(
          "Failed to execute the method - "
              + Thread.currentThread().getStackTrace()[1].getMethodName(),
          e);
    }
    return sortOrders;
  }

  @Override
  public List<Order> sortOrderByExecutionDate() {
    List<Order> sortOrders = null;
    try {
      sortOrders = orderRepository.getAll();
      sortOrders.sort((Comparator.comparing(Order::getExecution)));
      logger.info(
          "Method completed - " + Thread.currentThread().getStackTrace()[1].getMethodName());
    } catch (Exception e) {
      logger.warn(
          "Failed to execute the method - "
              + Thread.currentThread().getStackTrace()[1].getMethodName(),
          e);
    }
    return sortOrders;
  }

  @Override
  public List<Order> sortOrderByPrice() {
    List<Order> sortOrders = null;
    try {
      sortOrders = orderRepository.getAll();
      sortOrders.sort((Comparator.comparingInt(Order::getPrice)));
      logger.info(
          "Method completed - " + Thread.currentThread().getStackTrace()[1].getMethodName());
    } catch (Exception e) {
      logger.warn(
          "Failed to execute the method - "
              + Thread.currentThread().getStackTrace()[1].getMethodName(),
          e);
    }
    return sortOrders;
  }

  @Override
  public List<Order> sortOrderByStatus() {
    List<Order> sortOrders = null;
    try {
      sortOrders = orderRepository.getAll();
      sortOrders.sort((Comparator.comparing(Order::getOrderStatus)));
      logger.info(
          "Method completed - " + Thread.currentThread().getStackTrace()[1].getMethodName());
    } catch (Exception e) {
      logger.warn(
          "Failed to execute the method - "
              + Thread.currentThread().getStackTrace()[1].getMethodName(),
          e);
    }
    return sortOrders;
  }
}

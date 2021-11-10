package com.moon.senla.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.moon.senla.Availability;
import com.moon.senla.Book;
import com.moon.senla.BookRepository;
import com.moon.senla.Constant;
import com.moon.senla.Order;
import com.moon.senla.OrderDAO;
import com.moon.senla.OrderRepository;
import com.moon.senla.OrderService;
import com.moon.senla.OrderStatus;
import com.moon.senla.RequestRepository;
import com.moon.senla.annotations.InjectByType;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class OrderServiceImpl implements OrderService {
  @InjectByType private OrderRepository orderRepository;
  @InjectByType private BookRepository bookRepository;
  @InjectByType private RequestRepository requestRepository;
  @InjectByType private OrderDAO orderDAO;

  @Override
  public Order findOrderById(int id) {
    return orderRepository.findOrderById(id);
  }

  @Override
  public void addOrder(Order order) {
    orderDAO.create(order);
    orderDAO.createOrderBooks(order);
    if (checkBooksInOrder(order)) {
      closeOrder((int) order.getId());
      bookRepository.removeBooks(order.getBooks());
    } else {
      checkBooksForRequest(order);
    }
  }

  @Override
  public void checkBooksForRequest(Order order) {
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
  }

  @Override
  public boolean checkBooksInOrder(Order order) {
    boolean availability = true;
    for (Book book : order.getBooks()) {
      if (book.getAvailability().equals(Availability.OUT_OF_STOCK)) {
        availability = false;
      }
    }
    return availability;
  }

  @Override
  public void closeOrder(int id) {
    orderDAO.update(id);
  }

  @Override
  public void cancelOrder(int id) {
    orderDAO.updateCancel(id);
  }

  @Override
  public int getAllPriceOfSoldBooks(int months) {
    int price = 0;
    for (Order order : getCompletedOrderList(months)) {
      price += order.getPrice();
    }
    return price;
  }

  @Override
  public List<Order> getAll() {
    return orderRepository.getAll();
  }

  @Override
  public List<Order> getCompletedOrderList(int months) {
    List<Order> completedOrder = new ArrayList<>();
    for (Order order : orderRepository.getAll()) {
      if (order.getOrderStatus().equals(OrderStatus.COMPLETED)
          && order.getExecution().isAfter(LocalDate.now().minusMonths(months))) {
        completedOrder.add(order);
      }
    }
    return completedOrder;
  }

  @Override
  public List<Order> sortCompletedOrderByExecutionDate(int months) {
    List<Order> sortOrders = getCompletedOrderList(months);
    sortOrders.sort((Comparator.comparing(Order::getExecution)));
    return sortOrders;
  }

  @Override
  public List<Order> sortCompletedOrderByPrice(int months) {
    List<Order> sortOrders = getCompletedOrderList(months);
    sortOrders.sort((Comparator.comparingInt(Order::getPrice)));
    return sortOrders;
  }

  @Override
  public List<Order> sortOrderByExecutionDate() {
    List<Order> sortOrders = orderRepository.getAll();
    sortOrders.sort((Comparator.comparing(Order::getExecution)));
    return sortOrders;
  }

  @Override
  public List<Order> sortOrderByPrice() {
    List<Order> sortOrders = orderRepository.getAll();
    sortOrders.sort((Comparator.comparingInt(Order::getPrice)));
    return sortOrders;
  }

  @Override
  public List<Order> sortOrderByStatus() {
    List<Order> sortOrders = orderRepository.getAll();
    sortOrders.sort((Comparator.comparing(Order::getOrderStatus)));
    return sortOrders;
  }

  @Override
  public void writeOrderBd() {
    ObjectMapper mapper = new ObjectMapper();
    try (PrintWriter writer =
        new PrintWriter(new BufferedWriter(new FileWriter(Constant.FILE_ORDERS)))) {
      List<Order> orders = orderRepository.getAll();
      String orderJson = mapper.writeValueAsString(orders);
      writer.write(orderJson);
      writer.flush();
    } catch (IOException e) {
      System.out.println("Writing orders error");
    }
  }

  @Override
  public void readOrderBd() {
    ObjectMapper mapper = new ObjectMapper();
    List<Order> orders;
    try (BufferedReader reader = new BufferedReader(new FileReader(Constant.FILE_ORDERS))) {
      String orderJson;
      orderRepository.getAll().clear();
      while ((orderJson = reader.readLine()) != null) {
        orders = Arrays.asList(mapper.readValue(orderJson, Order[].class));
        for (Order order : orders) {
          getAll().add(order);
        }
      }
    } catch (IOException e) {
      System.out.println(e);
    }
  }
}

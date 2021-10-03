package task4.service.impl;

import static task4.UI.Constant.FILE_ORDERS;

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
import task4.model.Availability;
import task4.model.Book;
import task4.model.Order;
import task4.model.OrderStatus;
import task4.model.Request;
import task4.repository.BookRepository;
import task4.repository.OrderRepository;
import task4.repository.RequestRepository;
import task4.service.OrderService;

public class OrderServiceImpl implements OrderService {
  private final OrderRepository orderRepository;
  private final BookRepository bookRepository;
  private final RequestRepository requestRepository;

  public OrderServiceImpl(
      OrderRepository orderRepository,
      BookRepository bookRepository,
      RequestRepository requestRepository) {
    this.orderRepository = orderRepository;
    this.bookRepository = bookRepository;
    this.requestRepository = requestRepository;
  }

  @Override
  public Order findOrderById(int id) {
    return orderRepository.findOrderById(id);
  }

  @Override
  public void addOrder(Order order) {
    orderRepository.getAll().add(order);
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
  public void closeOrder(int id) {
    orderRepository.getAll().get(id - 1).setOrderStatusCompleate();
  }

  @Override
  public void cancelOrder(int id) {
    orderRepository.getAll().get(id - 1).setOrderStatusCanceled();
  }

  @Override
  public int getAllPriceOfSoldBooks(int months) {
    int price = 0;
    for (int i = 0; i < orderRepository.getAll().size(); i++) {
      if (orderRepository.getAll().get(i).getOrderStatus().equals(OrderStatus.COMPLETED)
          && orderRepository
              .getAll()
              .get(i)
              .getExecution()
              .isAfter(LocalDate.now().minusMonths(months))) {
        price += orderRepository.findOrderById(i + 1).getPrice();
      }
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
    getCompletedOrderList(months).sort((Comparator.comparing(Order::getExecution)));
    return sortOrders;
  }

  @Override
  public List<Order> sortCompletedOrderByPrice(int months) {
    List<Order> sortOrders = getCompletedOrderList(months);
    getCompletedOrderList(months).sort((Comparator.comparingInt(Order::getPrice)));
    return sortOrders;
  }

  @Override
  public List<Order> sortOrderByExecutionDate() {
    List<Order> sortOrders = orderRepository.getAll();
    orderRepository.getAll().sort((Comparator.comparing(Order::getExecution)));
    return sortOrders;
  }

  @Override
  public List<Order> sortOrderByPrice() {
    List<Order> sortOrders = orderRepository.getAll();
    orderRepository.getAll().sort((Comparator.comparingInt(Order::getPrice)));
    return sortOrders;
  }

  @Override
  public List<Order> sortOrderByStatus() {
    List<Order> sortOrders = orderRepository.getAll();
    orderRepository.getAll().sort((Comparator.comparing(Order::getOrderStatus)));
    return sortOrders;
  }

  @Override
  public void updateOrderCsv() {
    try {
      PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(FILE_ORDERS)));
      for (Order order : orderRepository.getAll()) {
        writer.println(order.getId() + "|" + order.getCustomerName() + "|" + order.getBooksId());
      }
      writer.flush();
      writer.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void downloadOrderCsv() {
    try {
      try (BufferedReader reader = new BufferedReader(new FileReader(FILE_ORDERS))) {
        String someOrder;
        orderRepository.getAll().clear();
        while ((someOrder = reader.readLine()) != null) {
          List<Book> orderBooks = new ArrayList<>();
          String[] values = someOrder.split("\\|");
          long id = Long.parseLong(values[0]);
          String name = values[1];
          List<String> listlong = Arrays.asList(values[2].split(","));
          for (String l : listlong) {
            for (Book book : bookRepository.getAll()) {
              if (Long.parseLong(l) == book.getId()) {
                orderBooks.add(book);
              }
            }
          }
          orderRepository.getAll().add(new Order(id, name, orderBooks));
        }
      }
    } catch (IOException e) {
      System.out.println("Loading error");
    }
  }
}

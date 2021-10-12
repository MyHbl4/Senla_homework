package task4.service.impl;

import static task4.util.Constant.FILE_ORDERS;
import static task4.util.Constant.FILE_ORDERS1;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import task4.DI.injector.BeanFactory;
import task4.enums.Availability;
import task4.enums.OrderStatus;
import task4.model.Book;
import task4.model.Order;
import task4.repository.BookRepository;
import task4.repository.OrderRepository;
import task4.repository.RequestRepository;
import task4.repository.impl.BookRepositoryImpl;
import task4.repository.impl.OrderRepositoryImpl;
import task4.repository.impl.RequestRepositoryImpl;
import task4.service.OrderService;

public class OrderServiceImpl implements OrderService {
  private final OrderRepository orderRepository =
      BeanFactory.getInstance().getBean(OrderRepositoryImpl.class);
  private final BookRepository bookRepository =
      BeanFactory.getInstance().getBean(BookRepositoryImpl.class);
  private final RequestRepository requestRepository =
      BeanFactory.getInstance().getBean(RequestRepositoryImpl.class);

  @Override
  public Order findOrderById(int id) {
    return orderRepository.findOrderById(id);
  }

  @Override
  public void addOrder(Order order) {
    orderRepository.getAll().add(order);
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
    orderRepository.findOrderById(id).setOrderStatusCompleate();
  }

  @Override
  public void cancelOrder(int id) {
    orderRepository.findOrderById(id).setOrderStatusCanceled();
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
  public void writeOrderBd() {
    ObjectMapper mapper = new ObjectMapper();
    try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(FILE_ORDERS)))) {
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
    try (BufferedReader reader = new BufferedReader(new FileReader(FILE_ORDERS))) {
      String orderJson;
      orderRepository.getAll().clear();
      while ((orderJson = reader.readLine()) != null) {
        orders = Arrays.asList(mapper.readValue(orderJson, Order[].class));
        for (Order order : orders) {
          getAll().add(order);
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

//  @Override
//  public void updateOrderCsv() {
//    try {
//      PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(FILE_ORDERS1)));
//      for (Order order : orderRepository.getAll()) {
//        writer.println(order.getId() + "|" + order.getCustomerName() + "|" + order.getBooksId());
//      }
//      writer.flush();
//      writer.close();
//    } catch (IOException e) {
//      e.printStackTrace();
//    }
//  }
//
//  @Override
//  public void downloadOrderCsv() {
//    try {
//      try (BufferedReader reader = new BufferedReader(new FileReader(FILE_ORDERS1))) {
//        String someOrder;
//        orderRepository.getAll().clear();
//        while ((someOrder = reader.readLine()) != null) {
//          List<Book> orderBooks = new ArrayList<>();
//          String[] values = someOrder.split("\\|");
//          long id = Long.parseLong(values[0]);
//          String name = values[1];
//          String[] listlong = values[2].split(",");
//          for (String l : listlong) {
//            for (Book book : bookRepository.getAll()) {
//              if (Long.parseLong(l) == book.getId()) {
//                orderBooks.add(book);
//              }
//            }
//          }
//          orderRepository.getAll().add(new Order(id, name, orderBooks));
//        }
//      }
//    } catch (IOException e) {
//      System.out.println("Loading error");
//    }
//  }
}

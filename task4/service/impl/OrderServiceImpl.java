package task4.service.impl;

import java.time.LocalDate;
import java.util.Arrays;
import task4.comporator.order.SortOrderByExecutionDate;
import task4.comporator.order.SortOrderByPrice;
import task4.comporator.order.SortOrderByStatus;
import task4.model.Availability;
import task4.model.Book;
import task4.model.Order;
import task4.model.OrderStatus;
import task4.model.Request;
import task4.repository.BookRepository;
import task4.repository.BuyerRepository;
import task4.repository.OrderRepository;
import task4.repository.RequestRepository;
import task4.service.OrderService;

public class OrderServiceImpl implements OrderService {
  private final OrderRepository orderRepository;
  private final BuyerRepository buyerRepository;
  private final BookRepository bookRepository;
  private final RequestRepository requestRepository;

  public OrderServiceImpl(
      OrderRepository orderRepository,
      BuyerRepository buyerRepository,
      BookRepository bookRepository,
      RequestRepository requestRepository) {
    this.orderRepository = orderRepository;
    this.buyerRepository = buyerRepository;
    this.bookRepository = bookRepository;
    this.requestRepository = requestRepository;
  }

  @Override
  public void addOrder(Order order) {
    System.out.println("Order added!");
    orderRepository.getAll().add(order);
    for (Book book : order.getBookInOrder()) {
      for (int i = 0; i < bookRepository.getAll().size(); i++) {
        if (bookRepository.findBookById(i).getAvailability().equals(Availability.OUT_OF_STOCK)
            && book.getTitle().equals(bookRepository.findBookById(i).getTitle())) {
          requestRepository.getAll().add(new Request(bookRepository.findBookById(i).getTitle()));
        }
      }
    }
  }

  @Override
  public void closeOrder(int id) {
    orderRepository.getAll().get(id - 1).setOrderStatusCompleate();
    System.out.println("The order is closed!");
  }

  @Override
  public void cancelOrder(int id) {
    orderRepository.getAll().get(id - 1).setOrderStatusCanceled();
    System.out.println("The order was canceled!");
  }

  @Override
  public void printOrderRepository() {
    System.out.println("List of all orders:");
    orderRepository.getAll().print();
  }

  @Override
  public int getPriceOfSoldBooksInOrder(int id) {
    int price = orderRepository.findOrderById(id).getPrice();
    return price;
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
  public void orderInfoById(int id) {
    Order order = orderRepository.findOrderById(id);
    System.out.print(
        "Order ID: "
            + order.getId()
            + ", Buyer: "
            + buyerRepository.findBuyerById(id).getName()
            + ", Price: "
            + order.getPrice()
            + ", Status: "
            + order.getOrderStatus()
            + ", ");
    order.showBooks();
  }

  @Override
  public int getCountSoldBooks(int months) {
    int count = 0;
    for (int i = 0; i < orderRepository.getAll().size(); i++) {
      if (orderRepository.getAll().get(i).getOrderStatus().equals(OrderStatus.COMPLETED)
          && orderRepository
              .getAll()
              .get(i)
              .getExecution()
              .isAfter(LocalDate.now().minusMonths(months))) {
        count += orderRepository.getAll().get(i).getBooks().length;
      }
    }
    return count;
  }

  @Override
  public void outputArray(Order[] orders) {
    for (Order order : orders) {
      System.out.println(order);
    }
  }

  @Override
  public Order[] sortOrderByStatus() {
    Order[] sortOrder = orderRepository.getArray(orderRepository.getAll());
    Arrays.sort(sortOrder, new SortOrderByStatus());
    return sortOrder;
  }

  @Override
  public Order[] sortOrderByPrice() {
    Order[] sortOrder = orderRepository.getArray(orderRepository.getAll());
    Arrays.sort(sortOrder, new SortOrderByPrice());
    return sortOrder;
  }

  @Override
  public Order[] sortOrderByExecutionDate() {
    Order[] sortOrder = orderRepository.getArray(orderRepository.getAll());
    Arrays.sort(sortOrder, new SortOrderByExecutionDate());
    return sortOrder;
  }

  @Override
  public Order[] sortCompletedOrderByPrice(int months) {
    Order[] sortOrder = orderRepository.getArrayCompletedOrder(months);
    Arrays.sort(sortOrder, new SortOrderByPrice());
    return sortOrder;
  }

  @Override
  public Order[] sortCompletedOrderByExecutionDate(int months) {
    Order[] sortOrder = orderRepository.getArrayCompletedOrder(months);
    Arrays.sort(sortOrder, new SortOrderByExecutionDate());
    return sortOrder;
  }
}

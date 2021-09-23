package task4.service.impl;

import java.time.LocalDate;
import task4.model.Availability;
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
  public void addRequest(String request) {
    int goodJob = 0;
    for (int i = 0; i < requestRepository.getAll().size(); i++) {
      if (requestRepository.getAll().get(i).getTitle().equals(request)) {
        requestRepository
            .getAll()
            .get(i)
            .setCount(requestRepository.getAll().get(i).getCount() + 1);
        goodJob = 1;
      }
    }
    if (goodJob == 0) {
      requestRepository.getAll().add(new Request(request));
    }
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
            addRequest(bookRepository.getAll().get(k).getTitle());
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
  public void getAllPriceOfSoldBooks(int months) {
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
    System.out.println("Revenue for " + months + " months amounted to: " + price);
  }

  @Override
  public void getCompletedOrder(int months) {
    int count = 0;
    int bookCount = 0;
    for (Order order : orderRepository.getCompletedOrder(months)) {
      bookCount += order.getBooks().size();
      count++;
    }
    System.out.println(
        "In "
            + months
            + " months, "
            + count
            + " orders were completed, "
            + bookCount
            + " books were sold");
  }

  @Override
  public void orderInfoById(int id) {
    Order order = orderRepository.findOrderById(id);
    System.out.print(
        "Order ID: "
            + order.getId()
            + ", Customer="
            + order.getCustomerName()
            + ", Price: "
            + order.getPrice()
            + ", Status: "
            + order.getOrderStatus()
            + ", ");
    order.showBooks();
  }

  @Override
  public void sortOrderByStatus() {
    orderRepository.getAll().sort(((o1, o2) -> o1.getOrderStatus().compareTo(o2.getOrderStatus())));
    orderRepository.getAll().stream().forEach(System.out::println);
  }

  @Override
  public void sortOrderByPrice() {
    orderRepository.getAll().sort(((o1, o2) -> o1.getPrice() - o2.getPrice()));
    orderRepository.getAll().stream().forEach(System.out::println);
  }

  @Override
  public void sortOrderByExecutionDate() {
    orderRepository.getAll().sort(((o1, o2) -> o1.getExecution().compareTo(o2.getExecution())));
    orderRepository.getAll().stream().forEach(System.out::println);
  }

  @Override
  public void sortCompletedOrderByPrice(int months) {
    orderRepository.getCompletedOrder(months).sort(((o1, o2) -> o1.getPrice() - o2.getPrice()));
    orderRepository.getCompletedOrder(months).stream().forEach(System.out::println);
  }

  @Override
  public void sortCompletedOrderByExecutionDate(int months) {
    orderRepository
        .getCompletedOrder(months)
        .sort(((o1, o2) -> o1.getExecution().compareTo(o2.getExecution())));
    orderRepository.getCompletedOrder(months).stream().forEach(System.out::println);
  }
}

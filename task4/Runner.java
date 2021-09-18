package task4;

import java.time.LocalDate;
import task4.datasource.BookDataSource;
import task4.datasource.BuyerDataSource;
import task4.datasource.OrderDataSource;
import task4.datasource.RequestDataSource;
import task4.datasource.impl.BookDataSourceImpl;
import task4.datasource.impl.BuyerDataSourceImpl;
import task4.datasource.impl.OrderDataSourceImpl;
import task4.datasource.impl.RequestDataSourceImpl;
import task4.model.Book;
import task4.model.Buyer;
import task4.model.EnumBook;
import task4.model.Genre;
import task4.model.Order;
import task4.repository.BookRepository;
import task4.repository.BuyerRepository;
import task4.repository.OrderRepository;
import task4.repository.RequestRepository;
import task4.repository.impl.BookRepositoryImpl;
import task4.repository.impl.BuyerRepositoryImpl;
import task4.repository.impl.OrderRepositoryImpl;
import task4.repository.impl.RequestRepositoryImpl;
import task4.service.BookService;
import task4.service.BuyerService;
import task4.service.OrderService;
import task4.service.RequestService;
import task4.service.impl.BookServiceImpl;
import task4.service.impl.BuyerServiceImpl;
import task4.service.impl.OrderServiceImpl;
import task4.service.impl.RequestServiceImpl;

public class Runner {

  public static void main(String[] args) {

    BookDataSource bookDataSource = new BookDataSourceImpl();
    BuyerDataSource buyerDataSource = new BuyerDataSourceImpl();
    OrderDataSource orderDataSource = new OrderDataSourceImpl();
    RequestDataSource requestDataSource = new RequestDataSourceImpl();
    BookRepository bookRepository = new BookRepositoryImpl(bookDataSource);
    BuyerRepository buyerRepository = new BuyerRepositoryImpl(buyerDataSource);
    OrderRepository orderRepository = new OrderRepositoryImpl(orderDataSource, bookDataSource);
    RequestRepository requestRepository = new RequestRepositoryImpl(requestDataSource);
    BookService bookService = new BookServiceImpl(bookRepository);
    BuyerService buyerService = new BuyerServiceImpl(buyerRepository);
    OrderService orderService = new OrderServiceImpl(orderRepository, buyerRepository, bookRepository, requestRepository);
    RequestService requestService = new RequestServiceImpl(requestRepository);

    // ========Book========
    bookService.printBookRepository(); // просмотреть список книг в БД
    bookService.addBook(
        new Book(
            11,
            "Мастер и Маргарита",
            "Михаил Булгаков",
            50,
            Genre.NOVEL,
            1966)); // добавление книги в БД автоматически статус книги IN_STOCK
    bookService.inStock(4); // переводит книгу по её id в статус IN_STOCK
    bookService.outOfStock(1); // переводит книгу по её id в статус OUT_OF_STOCK
    bookService.removeBook(11); // удалить книгу с БД
    System.out.println(bookRepository.findBookById(5)); // найти и посмотреть книгу по id в БД

    // ========Buyer========
    buyerService.printBuyerRepository(); // просмотреть список покупателей в БД
    buyerService.addBuyer(
        new Buyer(4, "Лара Крофт", 30, "Гомель ул.Войкова 81")); // добавить нового клиента в БД

    // ========Order========
    orderService.printOrderRepository(); // просмотреть список заказов в БД
//    orderService.addOrder(
//        new Order(
//            4,
//            2,
//            new Object[] {
//              EnumBook.ROMEO_AND_JULIET
//            })); // добавить новый заказ автоматически его статус NEW
    orderService.cancelOrder(2); // отменить заказ по его номеру в БД и его статус CANCELED
    orderService.closeOrder(1); // закрыть заказ по его номеру в БД и его статус COMPLETED

    bookService.printBookRepository(); // просмотреть список книг в БД
    buyerService.printBuyerRepository(); // просмотреть список покупателей в БД
    orderService.printOrderRepository(); // просмотреть список заказов в БД

    // =========Lesson 5========
    System.out.println("*************** LESSON 5 ******************");
    // лист запросов по количеству запросов
    // лист запросов по имени

    // лист книг по имени
    System.out.println("sort book by title");
    bookService.outputArray(bookService.sortBookByTitle());
    // лист книг по дате написания
    System.out.println("sort book by date");
    bookService.outputArray(bookService.sortBookByPublishedDate());
    // лист книг по цене
    System.out.println("sort book by price");
    bookService.outputArray(bookService.sortBookByPrice());
    // лист книг по наличию
    System.out.println("sort book by availability");
    bookService.outputArray(bookService.sortBookByAvailability());
    // лист заказов по дате исполнения
    System.out.println("sort order by date");
    orderService.outputArray(orderService.sortOrderByExecutionDate());
    // лист заказов по статусу
    System.out.println("sort order by status");
    orderService.outputArray(orderService.sortOrderByStatus());
    // лист заказов по цене
    System.out.println("sort order by price");
    orderService.outputArray(orderService.sortOrderByPrice());

    // лист запросов по количеству запросов
    // лист запросов по имени

    // лист выполненных заказов за период времени по дате выполнения(указать количество месяцев)
    System.out.println("sort completed order by execution date");
    orderService.outputArray(orderService.sortCompletedOrderByExecutionDate(6));
    // лист выполненных заказов за период времени по цене(указать количество месяцев)
    System.out.println("sort completed order by price");
    orderService.outputArray(orderService.sortCompletedOrderByPrice(6));
    // изменяем для теста даты поставки двух книг
    bookDataSource.getBooks().get(1).setDeliveryDate(LocalDate.of(2018, 9, 17));
    bookDataSource.getBooks().get(2).setDeliveryDate(LocalDate.of(2021, 1, 14));

    // лист залежавшиеся книги больше 6 месяцев по дате поступления
    System.out.println("sort old books by delivery date");
    bookService.outputArray(bookService.sortOldBookByDeliveryDate());
    // лист залежавшиеся книги больше 6 месяцев по цене
    System.out.println("sort old books by price");
    bookService.outputArray(bookService.sortOldBookByPrice());

    // сумма выручки за период времени(указать количество месяцев)
    System.out.println("revenue for the time period");
    System.out.println(orderService.getAllPriceOfSoldBooks(6));
    // количество выполненных заказов
    System.out.println("count by sold books");
    System.out.println(orderService.getCountSoldBooks(6));
    // заказ инфо
    System.out.println("order info by id");
    orderService.orderInfoById(1);
    // книга инфо
    System.out.println("book info by id");
    bookService.bookInfoById(1);
    // количество выручки с заказа по id
    System.out.println("order price by id");
    System.out.println(orderService.getPriceOfSoldBooksInOrder(2));
    // сумма выручки за всё время
    System.out.println("revenue for all time");
    System.out.println(orderService.getAllPriceOfSoldBooks(6));
    System.out.println();
    System.out.println(requestDataSource.getRequest());
  }
}

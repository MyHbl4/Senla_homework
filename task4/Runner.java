package task4;

import task4.datasource.BookDataSource;
import task4.datasource.BookDataSourceImpl;
import task4.datasource.BookRepository;
import task4.datasource.BookRepositoryImpl;
import task4.datasource.BuyerDataSource;
import task4.datasource.BuyerDataSourceImpl;
import task4.datasource.BuyerRepository;
import task4.datasource.BuyerRepositoryImpl;
import task4.datasource.OrderDataSource;
import task4.datasource.OrderDataSourceImpl;
import task4.datasource.OrderRepository;
import task4.datasource.OrderRepositoryImpl;
import task4.model.Book;
import task4.model.Buyer;
import task4.model.Genre;
import task4.model.Order;

public class Runner {

  public static void main(String[] args) {
    BookDataSource bookDataSource = new BookDataSourceImpl();
    BuyerDataSource buyerDataSource = new BuyerDataSourceImpl();
    OrderDataSource orderDataSource = new OrderDataSourceImpl();
    BookRepository bookRepository = new BookRepositoryImpl(bookDataSource);
    BuyerRepository buyerRepository = new BuyerRepositoryImpl(buyerDataSource);
    OrderRepository orderRepository = new OrderRepositoryImpl(orderDataSource);

    // ========работа с БД книг========
    bookRepository.printBookRepository(); // просмотреть список книг в БД
    bookRepository.addBook(
        new Book(
            11,
            "Мастер и Маргарита",
            "Михаил Булгаков",
            50,
            Genre.NOVEL)); // добавление книги в БД автоматически статус книги IN_STOCK
    bookRepository.inStock(4); // переводит книгу по её id  в статус IN_STOCK
    bookRepository.outOfStock(1); // переводит книгу по её id в статус OUT_OF_STOCK
    bookRepository.removeBook(11); // удалить книгу с БД
    System.out.println(bookRepository.findBookById(5)); // найти и посмотреть книгу по id в БД

    // ========работа с БД клиентов========
    buyerRepository.printBuyerRepository(); // просмотреть список покупателей в БД
    buyerRepository.addBuyer(
        new Buyer(4, "Лара Крофт", 30, "Гомель ул.Войкова 81")); // добавить нового клиента в БД

    // ========работа с БД заказов========
    orderRepository.printOrderRepository(); // просмотреть список заказов в БД
    orderRepository.addOrder(
        new Order(4, 2, new int[] {4, 10})); // добавить новый заказ автоматически его статус NEW
    orderRepository.cancelOrder(2); // отменить заказ по его номеру в БД и его статус CANCELED
    orderRepository.closeOrder(1); // закрыть заказ по его номеру в БД и его статус COMPLETED

    bookRepository.printBookRepository(); // просмотреть список книг в БД
    buyerRepository.printBuyerRepository(); // просмотреть список покупателей в БД
    orderRepository.printOrderRepository(); // просмотреть список заказов в БД
  }
}

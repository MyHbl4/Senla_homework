package com.moon.senla.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.moon.senla.BookService;
import com.moon.senla.dao.BookDao;
import com.moon.senla.entity.Book;
import com.moon.senla.entity.Order;
import com.moon.senla.entity.Request;
import com.moon.senla.enums.OrderStatus;
import com.moon.senla.repository.BookRepository;
import com.moon.senla.repository.OrderRepository;
import com.moon.senla.repository.RequestRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class BookServiceImpl implements BookService {
    private static final Logger LOGGER = LoggerFactory.getLogger(BookServiceImpl.class);
    private final BookRepository bookRepository;
    private final RequestRepository requestRepository;
    private final OrderRepository orderRepository;
    private final BookDao bookDAO;

    @Value("${FUNCTION_ORDER}")
    private String FUNCTION_ORDER;

    @Value("${MONTHS_STALE_BOOKS}")
    private String MONTHS_STALE_BOOKS;

    @Autowired
    public BookServiceImpl(
            BookRepository bookRepository,
            RequestRepository requestRepository,
            OrderRepository orderRepository,
            BookDao bookDAO) {
        this.bookRepository = bookRepository;
        this.requestRepository = requestRepository;
        this.orderRepository = orderRepository;
        this.bookDAO = bookDAO;
    }

    @Override
    public Book findBookById(int id) {
        return bookRepository.findBookById(id);
    }

    @Override
    public void addBook(Book book) {
        try {
            if (!bookRepository.checkBookInBooks(book)) {
                System.out.println("книга есть в реквестах");
                bookRepository.restoreBook(book);
                System.out.println("книге сделали рестор ");
            } else {
                bookDAO.create(book);
                System.out.println("книга создана");
            }
            if (checkBookInRequests(book)) {
                System.out.println("книга в реквестах");
                if (checkBookInOrders(book) && (FUNCTION_ORDER.equals("on"))) {
                    System.out.println("книга есть в ордерах и функция включена");
                    checkOrder();
                } else {
                    removeBookRequest(book);
                }
            }
            LOGGER.info(
                    "Method completed - " + Thread.currentThread().getStackTrace()[1].getMethodName());
        } catch (Exception e) {
            LOGGER.warn(
                    "Failed to execute the method - "
                            + Thread.currentThread().getStackTrace()[1].getMethodName(),
                    e);
        }
    }

    private void checkOrder() {
        try {
            for (Order order : orderRepository.getAll()) {
                if (orderRepository.checkBooksInOrder(order)) {
                    order.setOrderStatusCompleate();
                    bookRepository.removeBooks(order.getBooks());
                }
            }
            LOGGER.info(
                    "Method completed - " + Thread.currentThread().getStackTrace()[1].getMethodName());
        } catch (Exception e) {
            LOGGER.warn(
                    "Failed to execute the method - "
                            + Thread.currentThread().getStackTrace()[1].getMethodName(),
                    e);
        }
    }

    @Override
    public void removeBook(int id) {
        bookRepository.removeBook(id);
    }

    @Override
    public boolean checkBookInRequests(Book book) {
        boolean availability = false;
        try {
            for (Request request : requestRepository.getAll()) {
                if (book.getTitle().equals(request.getBook().getTitle()) && request.getCount() > 0) {
                    availability = true;
                }
            }
            LOGGER.info(
                    "Method completed - " + Thread.currentThread().getStackTrace()[1].getMethodName());
        } catch (Exception e) {
            LOGGER.warn(
                    "Failed to execute the method - "
                            + Thread.currentThread().getStackTrace()[1].getMethodName(),
                    e);
        }
        return availability;
    }

    @Override
    public boolean checkBookInOrders(Book book) {
        boolean availability = false;
        for (Order order : orderRepository.getAll()) {
            if (order.getOrderStatus().equals(OrderStatus.NEW)) {
                for (Book book1 : order.getBooks()) {
                    if (book1.getTitle().equals(book.getTitle())) {
                        availability = true;
                    }
                }
            }
        }
        return availability;
    }

    @Override
    public void removeBookRequest(Book book) {
        try {
            if (checkBookInRequests(book)) {
                for (Request request : requestRepository.getAll()) {
                    if (book.getTitle().equals(request.getBook().getTitle()) && request.getCount() > 0) {
                        request.setCount(request.getCount() - 1);
                        break;
                    }
                }
            }
            LOGGER.info(
                    "Method completed - " + Thread.currentThread().getStackTrace()[1].getMethodName());
        } catch (Exception e) {
            LOGGER.warn(
                    "Failed to execute the method - "
                            + Thread.currentThread().getStackTrace()[1].getMethodName(),
                    e);
        }
    }

    @Override
    public List<Book> getOldBooks() {
        List<Book> oldBooks = null;
        try {
            oldBooks = new ArrayList<>();
            for (Book book : bookRepository.getAll()) {
                if (book.getDeliveryDate()
                        .isBefore(LocalDate.now().minusMonths(Long.parseLong(MONTHS_STALE_BOOKS)))) {
                    oldBooks.add(book);
                }
            }
            LOGGER.info(
                    "Method completed - " + Thread.currentThread().getStackTrace()[1].getMethodName());
        } catch (NumberFormatException e) {
            LOGGER.warn(
                    "Failed to execute the method - "
                            + Thread.currentThread().getStackTrace()[1].getMethodName(),
                    e);
        }
        return oldBooks;
    }

    @Override
    public List<Book> sortBookByAvailability() {
        List<Book> sortBooks = null;
        try {
            sortBooks = bookRepository.getAll();
            sortBooks.sort(Comparator.comparing(Book::getAvailability));
            LOGGER.info(
                    "Method completed - " + Thread.currentThread().getStackTrace()[1].getMethodName());
        } catch (Exception e) {
            LOGGER.warn(
                    "Failed to execute the method - "
                            + Thread.currentThread().getStackTrace()[1].getMethodName(),
                    e);
        }
        return sortBooks;
    }

    @Override
    public List<Book> sortBookByPrice() {
        List<Book> sortBooks = null;
        try {
            sortBooks = bookRepository.getAll();
            sortBooks.sort(Comparator.comparingInt(Book::getPrice));
            LOGGER.info(
                    "Method completed - " + Thread.currentThread().getStackTrace()[1].getMethodName());
        } catch (Exception e) {
            LOGGER.warn(
                    "Failed to execute the method - "
                            + Thread.currentThread().getStackTrace()[1].getMethodName(),
                    e);
        }
        return sortBooks;
    }

    @Override
    public List<Book> sortBookByPublication() {
        List<Book> sortBooks = null;
        try {
            sortBooks = bookRepository.getAll();
            sortBooks.sort(Comparator.comparingInt(Book::getPublication));
            LOGGER.info(
                    "Method completed - " + Thread.currentThread().getStackTrace()[1].getMethodName());
        } catch (Exception e) {
            LOGGER.warn(
                    "Failed to execute the method - "
                            + Thread.currentThread().getStackTrace()[1].getMethodName(),
                    e);
        }
        return sortBooks;
    }

    @Override
    public List<Book> sortBookByTitle() {
        List<Book> sortBooks = null;
        try {
            sortBooks = bookRepository.getAll();
            sortBooks.sort(Comparator.comparing(Book::getTitle));
            LOGGER.info(
                    "Method completed - " + Thread.currentThread().getStackTrace()[1].getMethodName());
        } catch (Exception e) {
            LOGGER.warn(
                    "Failed to execute the method - "
                            + Thread.currentThread().getStackTrace()[1].getMethodName(),
                    e);
        }
        return sortBooks;
    }

    @Override
    public List<Book> sortOldBookByDate() {
        List<Book> sortBooks = null;
        try {
            sortBooks = getOldBooks();
            sortBooks.sort(Comparator.comparing(Book::getDeliveryDate));
            LOGGER.info(
                    "Method completed - " + Thread.currentThread().getStackTrace()[1].getMethodName());
        } catch (Exception e) {
            LOGGER.warn(
                    "Failed to execute the method - "
                            + Thread.currentThread().getStackTrace()[1].getMethodName(),
                    e);
        }
        return sortBooks;
    }

    @Override
    public List<Book> sortOldBookByPrice() {
        List<Book> sortBooks = null;
        try {
            sortBooks = getOldBooks();
            sortBooks.sort(Comparator.comparingInt(Book::getPrice));
            LOGGER.info(
                    "Method completed - " + Thread.currentThread().getStackTrace()[1].getMethodName());
        } catch (Exception e) {
            LOGGER.warn(
                    "Failed to execute the method - "
                            + Thread.currentThread().getStackTrace()[1].getMethodName(),
                    e);
        }
        return sortBooks;
    }
}

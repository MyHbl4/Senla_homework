package com.moon.senla.impl;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.moon.senla.dao.BookDao;
import com.moon.senla.dao.OrderDao;
import com.moon.senla.dao.RequestDao;
import com.moon.senla.entity.Book;
import com.moon.senla.entity.Order;
import com.moon.senla.entity.Request;
import com.moon.senla.enums.Availability;
import com.moon.senla.repository.BookRepository;
import com.moon.senla.repository.OrderRepository;
import com.moon.senla.repository.RequestRepository;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class BookServiceImplTest {

    @Mock
    private BookRepository bookRepository;

    @Mock
    private BookDao bookDao;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private OrderDao orderDao;

    @Mock
    private RequestRepository requestRepository;

    @Mock
    private RequestDao requestDao;

    @InjectMocks
    private BookServiceImpl bookService;

    private List<Book> books = Arrays.asList(
        new Book("Test_title1", "Test_author1", 100, 2011),
        new Book("Test_title2", "Test_author2", 200, 2012),
        new Book("Test_title3", "Test_author3", 300, 2013));

    private List<Request> requests = Arrays.asList(
        new Request(new Book("Test_title", "Test_author", 200, 2021)),
        new Request(new Book("Test_title1", "Test_author1", 100, 2011)),
        new Request(new Book("Test_title2", "Test_author2", 200, 2012)),
        new Request(new Book("Test_title3", "Test_author3", 300, 2013)));

    private Book setBook() {
        Book book = new Book("Test_title", "Test_author", 200, 2021);
        book.setId(1L);
        return book;
    }

    @Test
    void findBookById() {
        Book findBook = setBook();
        when(bookService.findBookById((int) findBook.getId())).thenReturn(findBook);
        Book book = bookService.findBookById((int) findBook.getId());
        assertThat(book.getId(), equalTo(1L));
        assertThat(book.getTitle(), equalTo("Test_title"));
        assertThat(book.getAuthor(), equalTo("Test_author"));
        assertThat(book.getPrice(), equalTo(200));
        assertThat(book.getPublication(), equalTo(2021));
        assertThat(book.getAvailability(), equalTo(Availability.IN_STOCK));
        assertThat(book.getDeliveryDate(), equalTo(LocalDate.now()));
    }

    @Test
    public void addBookIfNotExists() {
        Book book = setBook();
        when(bookRepository.checkBookInBooks(book)).thenReturn(true);
        bookService.addBook(book);
        verify(bookDao, times(1)).create(book);
    }

    @Test
    void checkOrder() {
        List<Order> orders = new ArrayList<Order>();
        Order order = new Order("Test_customer", books);
        orders.add(order);
        when(orderRepository.getAll()).thenReturn(orders);
        when(orderRepository.checkBooksInOrder(order)).thenReturn(true);
        bookService.checkOrder();
        verify(orderDao, times(1)).update(order);
        verify(bookRepository, times(1)).removeBooks(order.getBooks());
    }


    @Test
    void removeBook() {
        Book book = setBook();
        when(bookDao.read((int) book.getId())).thenReturn(book);
        bookService.removeBook((int) book.getId());
        verify(bookDao, times(1)).update(book);
        assertThat(book.getAvailability(), equalTo(Availability.OUT_OF_STOCK));
    }

    @Test
    void checkBookInRequests() {
        Book book = setBook();
        when(requestRepository.getAll()).thenReturn(requests);
        boolean checkBook = bookService.checkBookInRequests(book);
        assertThat(checkBook, equalTo(true));
    }

    @Test
    void checkBookInOrders() {
        Book book = setBook();
        List<Order> orders = new ArrayList<Order>();
        Order order = new Order("Test_customer", books);
        orders.add(order);
        when(orderRepository.getAll()).thenReturn(orders);
        boolean checkBook = bookService.checkBookInOrders(book);
        assertThat(checkBook, equalTo(false));
    }

    @Test
    void removeBookRequest() {
        Book book = setBook();
        Request request = requests.get(0);
        request.setCount(1);
        when(requestRepository.getAll()).thenReturn(requests);
        when(requestRepository.findRequestById((int) request.getId())).thenReturn(request);
        Request findRequest = requestRepository.findRequestById((int) request.getId());
        bookService.removeBookRequest(book);
        verify(requestDao, times(1)).update(findRequest);
    }

    @Test
    void create() {
        Book book = setBook();
        bookService.create(book);
        verify(bookDao, times(1)).create(book);
    }

    @Test
    void read() {
        Book findBook = setBook();
        when(bookService.findBookById((int) findBook.getId())).thenReturn(findBook);
        Book book = bookService.findBookById((int) findBook.getId());
        assertThat(book.getId(), equalTo(1L));
        assertThat(book.getTitle(), equalTo("Test_title"));
        assertThat(book.getAuthor(), equalTo("Test_author"));
        assertThat(book.getPrice(), equalTo(200));
        assertThat(book.getPublication(), equalTo(2021));
        assertThat(book.getAvailability(), equalTo(Availability.IN_STOCK));
        assertThat(book.getDeliveryDate(), equalTo(LocalDate.now()));

    }

    @Test
    void readAll() {
        when(bookService.readAll()).thenReturn(books);
        List<Book> booksList = bookService.readAll();
        assertThat(booksList.size(), equalTo(3));
        assertThat(booksList.get(0), equalTo(books.get(0)));
        assertThat(booksList.get(1), equalTo(books.get(1)));
        assertThat(booksList.get(2), equalTo(books.get(2)));
    }

    @Test
    void update() {
        Book book = setBook();
        when(bookService.read((int) book.getId())).thenReturn(book);
        Book updateBook = bookService.read((int) book.getId());
        bookService.update(updateBook);
        verify(bookDao, times(1)).update(updateBook);
    }

    @Test
    void delete() {
        Book book = setBook();
        when(bookService.read((int) book.getId())).thenReturn(book);
        Book deleteBook = bookService.read((int) book.getId());
        bookService.delete(deleteBook);
        verify(bookDao, times(1)).delete(deleteBook);
    }
}
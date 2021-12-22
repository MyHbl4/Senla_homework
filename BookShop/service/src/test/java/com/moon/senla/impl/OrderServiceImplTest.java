package com.moon.senla.impl;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.moon.senla.dao.OrderDao;
import com.moon.senla.entity.Book;
import com.moon.senla.entity.Order;
import com.moon.senla.entity.Request;
import com.moon.senla.enums.OrderStatus;
import com.moon.senla.repository.BookRepository;
import com.moon.senla.repository.OrderRepository;
import com.moon.senla.repository.RequestRepository;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class OrderServiceImplTest {

    @Mock
    private BookRepository bookRepository;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private OrderDao orderDao;

    @Mock
    private RequestRepository requestRepository;

    @InjectMocks
    private OrderServiceImpl orderService;

    private List<Book> books = Arrays.asList(
        new Book("Test_title1", "Test_author1", 100, 2011),
        new Book("Test_title2", "Test_author2", 200, 2012),
        new Book("Test_title3", "Test_author3", 300, 2013));

    private List<Request> requests = Arrays.asList(
        new Request(new Book("Test_title1", "Test_author1", 100, 2011)),
        new Request(new Book("Test_title2", "Test_author2", 200, 2012)),
        new Request(new Book("Test_title3", "Test_author3", 300, 2013)));

    private List<Order> orders = Arrays.asList(
        new Order("Test_customer", books),
        new Order("Test_customer1", books),
        new Order("Test_customer2", books)
    );

    private Book setBook() {
        Book book = new Book("Test_title", "Test_author", 200, 2021);
        book.setId(1L);
        return book;
    }

    private Order setOrder() {
        Order order = new Order("Test_customer", books);
        order.setId(1L);
        return order;
    }

    @Test
    void addOrder() {
        Order order = setOrder();
        orderService.addOrder(order);
        verify(orderDao, times(1)).createOrderBooks(order);
        verify(bookRepository, times(1)).removeBooks(books);
    }

    @Test
    void checkBooksInOrder() {
        Order order = setOrder();
        when(orderDao.read((int) order.getId())).thenReturn(order);
        Order findOrder = orderDao.read((int) order.getId());
        boolean checkOrder = orderService.checkBooksInOrder(findOrder);
        assertThat(checkOrder, equalTo(true));
    }

    @Test
    void closeOrder() {
        Order order = setOrder();
        when(orderDao.read((int) order.getId())).thenReturn(order);
        Order findOrder = orderDao.read((int) order.getId());
        orderService.closeOrder((int) findOrder.getId());
        assertThat(findOrder.getOrderStatus(), equalTo(OrderStatus.COMPLETED));
    }

    @Test
    void cancelOrder() {
        Order order = setOrder();
        when(orderDao.read((int) order.getId())).thenReturn(order);
        Order findOrder = orderDao.read((int) order.getId());
        orderService.cancelOrder((int) findOrder.getId());
        assertThat(findOrder.getOrderStatus(), equalTo(OrderStatus.CANCELED));
    }

    @Test
    void getCompletedOrderList() {
        List<Order> orderList = orders;
        orderList.get(0).setOrderStatusCompleate();
        when(orderRepository.getAll()).thenReturn(orderList);
        List<Order> checkOrders = orderService.getCompletedOrderList(5);
        assertThat(checkOrders.size(), equalTo(1));
    }

    @Test
    void create() {
        Order order = setOrder();
        orderService.create(order);
        verify(orderDao, times(1)).create(order);
    }


    @Test
    void read() {
        Order findOrder = setOrder();
        when(orderService.read((int) findOrder.getId())).thenReturn(findOrder);
        Order order = orderService.read((int) findOrder.getId());
        assertThat(order.getId(), equalTo(1L));
        assertThat(order.getCustomerName(), equalTo("Test_customer"));
        assertThat(order.getBooks(), equalTo(books));
        assertThat(order.getOrderStatus(), equalTo(OrderStatus.NEW));
    }

    @Test
    void readAll() {
        when(orderService.readAll()).thenReturn(orders);
        List<Order> orderList = orderService.readAll();
        assertThat(orderList.size(), equalTo(3));
        assertThat(orderList.get(0), equalTo(orders.get(0)));
        assertThat(orderList.get(1), equalTo(orders.get(1)));
        assertThat(orderList.get(2), equalTo(orders.get(2)));
    }

    @Test
    void update() {
        Order order = setOrder();
        when(orderService.read((int) order.getId())).thenReturn(order);
        Order updateBook = orderService.read((int) order.getId());
        orderService.update(updateBook);
        verify(orderDao, times(1)).update(updateBook);
    }

    @Test
    void delete() {
        Order order = setOrder();
        when(orderService.read((int) order.getId())).thenReturn(order);
        Order deleteOrder = orderService.read((int) order.getId());
        orderService.delete(deleteOrder);
        verify(orderDao, times(1)).delete(deleteOrder);
    }
}
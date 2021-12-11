package com.moon.senla.impl;

import com.moon.senla.OrderService;
import com.moon.senla.dao.OrderDao;
import com.moon.senla.entity.Book;
import com.moon.senla.entity.Order;
import com.moon.senla.enums.Availability;
import com.moon.senla.enums.OrderStatus;
import com.moon.senla.repository.BookRepository;
import com.moon.senla.repository.OrderRepository;
import com.moon.senla.repository.RequestRepository;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderServiceImpl implements OrderService {
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderServiceImpl.class);
    private final OrderRepository orderRepository;
    private final BookRepository bookRepository;
    private final RequestRepository requestRepository;
    private final OrderDao orderDAO;

    @Autowired
    public OrderServiceImpl(
        OrderRepository orderRepository,
        BookRepository bookRepository,
        RequestRepository requestRepository,
        OrderDao orderDAO) {
        this.orderRepository = orderRepository;
        this.bookRepository = bookRepository;
        this.requestRepository = requestRepository;
        this.orderDAO = orderDAO;
    }

    @Override
    public Order findOrderById(int id) {
        return orderDAO.read(id);
    }

    @Override
    @Transactional
    public void addOrder(Order order) {
        try {
            orderDAO.createOrderBooks(order);
            if (checkBooksInOrder(order)) {
                closeOrder((int) order.getId());
                bookRepository.removeBooks(order.getBooks());
            } else {
                checkBooksForRequest(order);
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
    public void checkBooksForRequest(Order order) {
        try {
            for (int i = 0; i < order.getBooks().size(); i++) {
                if (order.getBooks().get(i).getTitle() != null) {
                    for (int k = 0; k < bookRepository.getAll().size(); k++) {
                        if (order.getBooks().get(i).getTitle()
                            .equals(bookRepository.getAll().get(k).getTitle())
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
    public boolean checkBooksInOrder(Order order) {
        boolean availability = false;
        try {
            availability = true;
            for (Book book : order.getBooks()) {
                if (book.getAvailability().equals(Availability.OUT_OF_STOCK)) {
                    availability = false;
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
    public void closeOrder(int id) {
        try {
            Order order = orderDAO.read(id);
            order.setOrderStatusCompleate();
            orderDAO.update(order);
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
    public void cancelOrder(int id) {
        try {
            Order order = orderDAO.read(id);
            order.setOrderStatusCanceled();
            orderDAO.update(order);
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
    public int getAllPriceOfSoldBooks(int months) {
        int price = 0;
        try {
            price = 0;
            for (Order order : getCompletedOrderList(months)) {
                price += order.getPrice();
            }
            LOGGER.info(
                "Method completed - " + Thread.currentThread().getStackTrace()[1].getMethodName());
        } catch (Exception e) {
            LOGGER.warn(
                "Failed to execute the method - "
                    + Thread.currentThread().getStackTrace()[1].getMethodName(),
                e);
        }
        return price;
    }

    @Override
    public List<Order> getCompletedOrderList(int months) {
        List<Order> completedOrder = null;
        try {
            completedOrder = new ArrayList<>();
            for (Order order : orderRepository.getAll()) {
                if (order.getOrderStatus().equals(OrderStatus.COMPLETED)
                    && order.getExecution().isAfter(LocalDate.now().minusMonths(months))) {
                    completedOrder.add(order);
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
        return completedOrder;
    }

    @Override
    public List<Order> sortCompletedOrderByExecutionDate(int months) {
        List<Order> sortOrders = null;
        try {
            sortOrders = getCompletedOrderList(months);
            sortOrders.sort((Comparator.comparing(Order::getExecution)));
            LOGGER.info(
                "Method completed - " + Thread.currentThread().getStackTrace()[1].getMethodName());
        } catch (Exception e) {
            LOGGER.warn(
                "Failed to execute the method - "
                    + Thread.currentThread().getStackTrace()[1].getMethodName(),
                e);
        }
        return sortOrders;
    }

    @Override
    public List<Order> sortCompletedOrderByPrice(int months) {
        List<Order> sortOrders = null;
        try {
            sortOrders = getCompletedOrderList(months);
            sortOrders.sort((Comparator.comparingInt(Order::getPrice)));
            LOGGER.info(
                "Method completed - " + Thread.currentThread().getStackTrace()[1].getMethodName());
        } catch (Exception e) {
            LOGGER.warn(
                "Failed to execute the method - "
                    + Thread.currentThread().getStackTrace()[1].getMethodName(),
                e);
        }
        return sortOrders;
    }

    @Override
    public List<Order> sortOrderByExecutionDate() {
        List<Order> sortOrders = null;
        try {
            sortOrders = orderRepository.getAll();
            sortOrders.sort((Comparator.comparing(Order::getExecution)));
            LOGGER.info(
                "Method completed - " + Thread.currentThread().getStackTrace()[1].getMethodName());
        } catch (Exception e) {
            LOGGER.warn(
                "Failed to execute the method - "
                    + Thread.currentThread().getStackTrace()[1].getMethodName(),
                e);
        }
        return sortOrders;
    }

    @Override
    public List<Order> sortOrderByPrice() {
        List<Order> sortOrders = null;
        try {
            sortOrders = orderRepository.getAll();
            sortOrders.sort((Comparator.comparingInt(Order::getPrice)));
            LOGGER.info(
                "Method completed - " + Thread.currentThread().getStackTrace()[1].getMethodName());
        } catch (Exception e) {
            LOGGER.warn(
                "Failed to execute the method - "
                    + Thread.currentThread().getStackTrace()[1].getMethodName(),
                e);
        }
        return sortOrders;
    }

    @Override
    public List<Order> sortOrderByStatus() {
        List<Order> sortOrders = null;
        try {
            sortOrders = orderRepository.getAll();
            sortOrders.sort((Comparator.comparing(Order::getOrderStatus)));
            LOGGER.info(
                "Method completed - " + Thread.currentThread().getStackTrace()[1].getMethodName());
        } catch (Exception e) {
            LOGGER.warn(
                "Failed to execute the method - "
                    + Thread.currentThread().getStackTrace()[1].getMethodName(),
                e);
        }
        return sortOrders;
    }

    @Override
    public void create(Order entity) {
        orderDAO.create(entity);
    }

    @Override
    public Order read(int id) {
        return orderDAO.read(id);
    }

    @Override
    public List<Order> readAll() {
        return orderDAO.readAll();
    }

    @Override
    public void update(Order entity) {
        orderDAO.update(entity);
    }

    @Override
    public void delete(Order entity) {
        orderDAO.delete(entity);
    }
}

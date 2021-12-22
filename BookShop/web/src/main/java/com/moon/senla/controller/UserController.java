package com.moon.senla.controller;

import com.moon.senla.BookService;
import com.moon.senla.OrderService;
import com.moon.senla.entity.Book;
import com.moon.senla.entity.Order;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/user")
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionController.class);

    @Value("${MONTHS_STALE_BOOKS}")
    private String MONTHS_STALE_BOOKS;

    private final BookService bookService;
    private final OrderService orderService;

    public UserController(BookService bookService, OrderService orderService) {
        this.bookService = bookService;
        this.orderService = orderService;
    }

    @GetMapping()
    public String index(Model model) {
        Iterable<Book> books = bookService.readAll();
        model.addAttribute("books", books);
        return "user/index";
    }

    @GetMapping("/new")
    public String newOrder(@ModelAttribute("order") Order order, Model model) {
        Iterable<Book> books = bookService.sortBookByAvailability();
        model.addAttribute("books", books);
        return "user/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("order") @Valid Order order,
        @RequestParam("orderBooksId") List<Integer> books_id) {
        List<Book> bookList = new ArrayList<>();
        for (Integer i : books_id) {
            bookList.add(bookService.findBookById(i));
        }
        order.setBooks(bookList);
        orderService.addOrder(order);
//        orderDao.create(order);
        return "redirect:/user";
    }
    @GetMapping("/sort/sort-book-price")
    public String sortPrice(Model model) {
        Iterable<Book> books = bookService.sortBookByPrice();
        model.addAttribute("books", books);
        return "user/index";
    }

    @GetMapping("/sort/sort-book-availability")
    public String sortAvailability(Model model) {
        Iterable<Book> books = bookService.sortBookByAvailability();
        model.addAttribute("books", books);
        return "user/index";
    }

    @GetMapping("/sort/sort-book-title")
    public String sortTitle(Model model) {
        Iterable<Book> books = bookService.sortBookByTitle();
        model.addAttribute("books", books);
        return "user/index";
    }

    @GetMapping("/sort/sort-book-publication")
    public String sortPublication(Model model) {
        Iterable<Book> books = bookService.sortBookByPublication();
        model.addAttribute("books", books);
        return "user/index";
    }

    @ExceptionHandler(value = Exception.class)
    public String handleException(HttpServletRequest request, Exception ex) {
        LOGGER.error("Request " + request.getRequestURL() + " Threw an exception", ex);
        return "error/error";
    }
}

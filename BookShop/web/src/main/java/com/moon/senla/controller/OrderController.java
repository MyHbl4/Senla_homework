package com.moon.senla.controller;

import com.moon.senla.BookService;
import com.moon.senla.OrderService;
import com.moon.senla.entity.Book;
import com.moon.senla.entity.Order;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/orders")
public class OrderController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionController.class);

    private final OrderService orderService;
    private final BookService bookService;

    @Autowired
    public OrderController(OrderService orderService,
        BookService bookService) {
        this.orderService = orderService;
        this.bookService = bookService;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("orders", orderService.readAll());
        return "orders/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("order", orderService.read(id));
        return "orders/show";
    }

    @GetMapping("/new")
    public String newOrder(@ModelAttribute("order") Order order, Model model) {
        Iterable<Book> books = bookService.sortBookByAvailability();
        model.addAttribute("books", books);
        return "orders/new";
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
        return "redirect:/orders";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("order", orderService.read(id));
        model.addAttribute("books", bookService.sortBookByAvailability());

        return "orders/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("order") @Valid Order order, @PathVariable("id") int id,
        @RequestParam("orderBooksId") List<Integer> books_id) {
        List<Book> bookList = new ArrayList<>();
        for (Integer i : books_id) {
            bookList.add(bookService.findBookById(i));
        }
        order.setBooks(bookList);
        orderService.update(order);
        return "redirect:/orders";
    }

    @PatchMapping("/{id}/cancel")
    public String cancel(@PathVariable("id") int id) {
        orderService.cancelOrder(id);
        return "redirect:/orders";
    }

    @PatchMapping("/{id}/close")
    public String close(@PathVariable("id") int id) {
        orderService.closeOrder(id);
        return "redirect:/orders";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        orderService.delete(orderService.read(id));
        return "redirect:/orders";
    }

    @GetMapping("/sort-order-price")
    public String sortOrderByPrice(Model model) {
        model.addAttribute("orders", orderService.sortOrderByPrice());
        return "orders/index";
    }

    @GetMapping("/sort-order-status")
    public String sortOrderByStatus(Model model) {
        model.addAttribute("orders", orderService.sortOrderByStatus());
        return "orders/index";
    }

    @GetMapping("/sort-order-execution")
    public String sortOrderByExecution(Model model) {
        model.addAttribute("orders", orderService.sortOrderByExecutionDate());
        return "orders/index";
    }

    @GetMapping("/sort-completed-order-price")
    public String sortCompletedOrderByPrice(Model model) {
        model.addAttribute("orders", orderService.sortCompletedOrderByPrice(6));
        return "orders/index";
    }

    @GetMapping("/sort-completed-order-date")
    public String sortCompletedOrderByExecutionDate(Model model) {
        model.addAttribute("orders", orderService.sortCompletedOrderByExecutionDate(6));
        return "orders/index";
    }

    @RequestMapping("/askDetails")
    public String askDetails() {
        return "orders/ask-details";
    }

    @RequestMapping("/showDetails")
    public String showDetails(Model model, @RequestParam("months") int months) {
        int price = 0;
        int count = 0;
        for (Order order : orderService.getCompletedOrderList(months)) {
            count++;
            price += order.getPrice();
        }
        model.addAttribute("orders", orderService.getCompletedOrderList(months));
        model.addAttribute("count",
            "In " + months + " months, " + count + " orders were completed");
        model.addAttribute("price", "Revenue for " + months + " months amounted to: " + price);
        return "orders/show-details";
    }

    @ExceptionHandler(value = Exception.class)
    public String handleException(HttpServletRequest request, Exception ex) {
        LOGGER.error("Request " + request.getRequestURL() + " Threw an exception", ex);
        return "error/error";
    }
}

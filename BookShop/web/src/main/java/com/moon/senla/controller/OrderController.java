package com.moon.senla.controller;

import com.moon.senla.BookService;
import com.moon.senla.OrderService;
import com.moon.senla.dao.OrderDao;
import com.moon.senla.entity.Book;
import com.moon.senla.entity.Order;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/orders")
public class OrderController {

    private final OrderDao orderDao;
    private final OrderService orderService;
    private final BookService bookService;

    @Autowired
    public OrderController(OrderDao orderDao, OrderService orderService,
        BookService bookService) {
        this.orderDao = orderDao;
        this.orderService = orderService;
        this.bookService = bookService;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("orders", orderDao.readAll());
        return "orders/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("order", orderDao.read(id));
        return "orders/show";
    }

    @GetMapping("/new")
    public String newOrder(@ModelAttribute("order") Order order, Model model) {
        Iterable<Book> books = bookService.sortBookByAvailability();
        model.addAttribute("books", books);return "orders/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("order") @Valid Order order,
        BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "orders/new";
        }

        orderDao.create(order);
        return "redirect:/orders";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("order", orderDao.read(id));
        return "orders/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("order") @Valid Order order,
        BindingResult bindingResult, @PathVariable("id") int id) {
        if (bindingResult.hasErrors()) {
            return "orders/edit";
        }

        orderDao.update(order);
        return "redirect:/orders";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        orderDao.delete(orderDao.read(id));
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

//    @GetMapping()
//    public String index(Model model) {
//        model.addAttribute("orders", orderService.sortCompletedOrderByPrice());
//        return "orders/index";
//    }
}

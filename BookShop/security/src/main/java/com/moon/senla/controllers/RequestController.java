package com.moon.senla.controllers;

import com.moon.senla.BookService;
import com.moon.senla.RequestService;
import com.moon.senla.entity.Book;
import com.moon.senla.entity.Request;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
@RequestMapping("/requests")
public class RequestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionController.class);

    private final RequestService requestService;
    private final BookService bookService;

    @Autowired
    public RequestController(RequestService requestService, BookService bookService) {
        this.requestService = requestService;
        this.bookService = bookService;
    }

    @GetMapping()
    public String index(Model model) {
        Iterable<Request> requests = requestService.readAll();
        model.addAttribute("requests", requests);
        return "requests/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("request", requestService.read(id));
        return "requests/show";
    }

    @GetMapping("/new")//путь по которому переходим на этот метод
    public String newRequest(@ModelAttribute("request") Request request, Model model) {
        Iterable<Book> books = bookService.sortBookByAvailability();
        model.addAttribute("books", books);
        return "requests/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("request") @Valid Request request,
        @RequestParam("bookId") int bookId) {
        requestService.addRequest(bookId);
        return "redirect:/requests";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("request", requestService.read(id));
        model.addAttribute("books", bookService.sortBookByAvailability());
        return "requests/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("request") @Valid Request request,
        BindingResult bindingResult, @PathVariable("id") int id,
        @RequestParam("bookId") int bookId) {
        if (bindingResult.hasErrors()) {
            return "requests/edit";
        }

        request.setBook(bookService.read(bookId));
        request.setTitle(bookService.read(bookId).getTitle());
        requestService.update(request);
        return "redirect:/requests";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        requestService.delete(requestService.read(id));
        return "redirect:/requests";
    }

    @GetMapping("/sort-request-count")
    public String sortRequestByCount(Model model) {
        Iterable<Request> requests = requestService.sortRequestByCount();
        model.addAttribute("requests", requests);
        return "requests/index";
    }

    @GetMapping("/sort-request-title")
    public String sortRequestByTitle(Model model) {
        Iterable<Request> requests = requestService.sortRequestByTitle();
        model.addAttribute("requests", requests);
        return "requests/index";
    }

    @ExceptionHandler(value = Exception.class)
    public String handleException(HttpServletRequest request, Exception ex) {
        LOGGER.error("Request " + request.getRequestURL() + " Threw an exception", ex);
        return "error2";
    }
}

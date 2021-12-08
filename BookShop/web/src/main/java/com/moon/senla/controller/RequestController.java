package com.moon.senla.controller;

import com.moon.senla.BookService;
import com.moon.senla.RequestService;
import com.moon.senla.dao.BookDao;
import com.moon.senla.dao.RequestDao;
import com.moon.senla.entity.Book;
import com.moon.senla.entity.Request;
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
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/requests")
public class RequestController {

    private final RequestDao requestDao;
    private final RequestService requestService;
    private final BookDao bookDao;
    private final BookService bookService;

    @Autowired
    public RequestController(RequestDao requestDao, RequestService requestService,
        BookDao bookDao, BookService bookService) {
        this.requestDao = requestDao;
        this.requestService = requestService;
        this.bookDao = bookDao;
        this.bookService = bookService;
    }

    @GetMapping()
    public String index(Model model) {
        Iterable<Request> requests = requestDao.readAll();
        model.addAttribute("requests", requests);
        return "requests/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("request", requestDao.read(id));
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
        BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "requests/new";
        }
        if (request.getBook()==null){
            return "requests/new";
        }
//        requestService.addRequest(request.getBook().getId());
        requestDao.create(request);
        return "redirect:/requests";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("request", requestDao.read(id));
        model.addAttribute("books", bookService.sortBookByAvailability());
        return "requests/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("request") @Valid Request request,
        BindingResult bindingResult, @PathVariable("id") int id) {
        if (bindingResult.hasErrors()) {
            return "requests/edit";
        }

        requestDao.update(request);
        return "redirect:/requests";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        requestDao.delete(requestDao.read(id));
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
}

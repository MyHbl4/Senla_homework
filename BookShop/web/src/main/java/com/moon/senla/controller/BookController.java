package com.moon.senla.controller;


import com.moon.senla.BookService;
import com.moon.senla.dao.BookDao;
import com.moon.senla.entity.Book;
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
@RequestMapping("/books")
public class BookController {

    private final BookDao bookDao;
    private final BookService bookService;

    @Autowired
    public BookController(BookDao bookDao, BookService bookService) {
        this.bookDao = bookDao;
        this.bookService = bookService;
    }

    @GetMapping()
    public String index(Model model) {
        Iterable<Book> books = bookDao.readAll();
        model.addAttribute("books", books);
        return "books/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("book", bookDao.read(id));
        return "books/show";
    }

    @GetMapping("/new")//путь по которому переходим на этот метод
    public String newBook(@ModelAttribute("book") Book book) {
        return "books/new";
    }

    @PostMapping()//метод добавления новых данных
    public String create(@ModelAttribute("book") @Valid Book book,
        BindingResult bindingResult) {
        if (bindingResult.hasErrors())//если аннотации выдают ошибку
            return "books/new";

        bookDao.create(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("book", bookDao.read(id));
        return "books/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("book") @Valid Book book,
        BindingResult bindingResult,
        @PathVariable("id") int id) {
        if (bindingResult.hasErrors()) {
            return "books/edit";
        }

        bookDao.update(book);
        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        bookDao.delete(bookDao.read(id));
        return "redirect:/books";
    }

    @GetMapping("/sort-price")
    public String sortPrice(Model model) {
        Iterable<Book> books = bookService.sortBookByPrice();
        model.addAttribute("books", books);
        return "books/index";
    }

    @GetMapping("/sort-availability")
    public String sortAvailability(Model model) {
        Iterable<Book> books = bookService.sortBookByAvailability();
        model.addAttribute("books", books);
        return "books/index";
    }

    @GetMapping("/sort-title")
    public String sortTitle(Model model) {
        Iterable<Book> books = bookService.sortBookByTitle();
        model.addAttribute("books", books);
        return "books/index";
    }

    @GetMapping("/sort-publication")
    public String sortPublication(Model model) {
        Iterable<Book> books = bookService.sortBookByPublication();
        model.addAttribute("books", books);
        return "books/index";
    }

    @GetMapping("/sort-old-date")
    public String sortOldBookByDate(Model model) {
        Iterable<Book> books = bookService.sortOldBookByDate();
        model.addAttribute("books", books);
        return "books/index";
    }

    @GetMapping("/sort-old-price")
    public String sortOldBookByPrice(Model model) {
        Iterable<Book> books = bookService.sortOldBookByPrice();
        model.addAttribute("books", books);
        return "books/index";
    }
}
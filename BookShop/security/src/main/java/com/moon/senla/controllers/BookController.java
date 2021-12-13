package com.moon.senla.controllers;


import com.moon.senla.BookService;
import com.moon.senla.entity.Book;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
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


@Controller
@RequestMapping("/books")
public class BookController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionController.class);

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping()
    public String index(Model model) {
        Iterable<Book> books = bookService.readAll();
        model.addAttribute("books", books);
        return "books/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("book", bookService.read(id));
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
        {
            return "books/new";
        }

        bookService.addBook(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("book", bookService.read(id));
        return "books/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("book") @Valid Book book,
        BindingResult bindingResult,
        @PathVariable("id") int id) {
        if (bindingResult.hasErrors()) {
            return "books/edit";
        }

        bookService.update(book);
        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        bookService.delete(bookService.read(id));
        return "redirect:/books";
    }

    @PatchMapping("/{id}/remove")
    public String remove(@PathVariable("id") int id) {
        bookService.removeBook(id);
        return "redirect:/books";
    }

    @GetMapping("/sort-book-price")
    public String sortPrice(Model model) {
        Iterable<Book> books = bookService.sortBookByPrice();
        model.addAttribute("books", books);
        return "books/index";
    }

    @GetMapping("/sort-book-availability")
    public String sortAvailability(Model model) {
        Iterable<Book> books = bookService.sortBookByAvailability();
        model.addAttribute("books", books);
        return "books/index";
    }

    @GetMapping("/sort-book-title")
    public String sortTitle(Model model) {
        Iterable<Book> books = bookService.sortBookByTitle();
        model.addAttribute("books", books);
        return "books/index";
    }

    @GetMapping("/sort-book-publication")
    public String sortPublication(Model model) {
        Iterable<Book> books = bookService.sortBookByPublication();
        model.addAttribute("books", books);
        return "books/index";
    }

    @GetMapping("/sort-old-book-date")
    public String sortOldBookDate(Model model) {
        List<Book> oldBooks = null;
        oldBooks = new ArrayList<>();
        for (Book book : bookService.readAll()) {
            if (book.getDeliveryDate()
                .isBefore(LocalDate.now().minusMonths(10))) {
                oldBooks.add(book);
            }
        }
        List<Book> sortBooks = oldBooks;
        sortBooks.sort(Comparator.comparing(Book::getDeliveryDate));
        model.addAttribute("books", sortBooks);
        return "books/index";
    }

    @GetMapping("/sort-old-book-price")
    public String sortOldBookPrice(Model model) {
        List<Book> oldBooks = null;
        oldBooks = new ArrayList<>();
        for (Book book : bookService.readAll()) {
            if (book.getDeliveryDate()
                .isBefore(LocalDate.now().minusMonths(10))) {
                oldBooks.add(book);
            }
        }
        List<Book> sortBooks = oldBooks;
        sortBooks.sort(Comparator.comparing(Book::getPrice));

        model.addAttribute("books", sortBooks);
        return "books/index";
    }

    @ExceptionHandler(value = Exception.class)
    public String handleException(HttpServletRequest request, Exception ex) {
        LOGGER.error("Request " + request.getRequestURL() + " Threw an exception", ex);
        return "error2";
    }
}
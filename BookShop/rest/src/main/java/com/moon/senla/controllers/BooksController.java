package com.moon.senla.controllers;

import com.moon.senla.BookService;
import com.moon.senla.entity.Book;
import com.moon.senla.exception.NoSuchBookException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/books")
public class BooksController {

    private final BookService bookService;

    @Autowired
    public BooksController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping()
    public List<Book> showAllBooks() {
        List<Book> bookList = bookService.readAll();
        return bookList;
    }

    @GetMapping("/{id}")
    public Book getBook(@PathVariable int id) {
        Book book = bookService.findBookById(id);

        if (book == null) {
            throw new NoSuchBookException("There is no book with ID = " + id + " in Database");
        }
        return book;
    }

    @PostMapping()
    public Book addBook(@RequestBody Book book) {
        bookService.addBook(book);
        return book;
    }

    @PutMapping()
    public Book updateBook(@RequestBody Book book) {
        bookService.update(book);
        return book;
    }
    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable int id){
        bookService.delete(bookService.findBookById(id));
        return "Book with ID = " + id + " was deleted";
    }
}

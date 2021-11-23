package com.moon.senla.action.bookAction;

import com.moon.senla.action.AbstractAction;
import com.moon.senla.action.Manager;
import com.moon.senla.entity.Book;
import org.springframework.stereotype.Component;

@Component
public class AddBookAction extends AbstractAction {

    public AddBookAction(Manager manager) {
        super(manager);
    }

    @Override
    public void execute() {
        System.out.println("Enter the name of the book");
        String title = customScanner.getString();
        System.out.println("Enter the author of the book");
        String author = customScanner.getString();
        System.out.println("Enter the price of the book");
        int price = customScanner.getInt();
        System.out.println("Enter the year of publication");
        int publication = customScanner.getInt();
        manager.getBookService().addBook(new Book(title, author, price, publication));
        System.out.println("The book has been added");
    }
}

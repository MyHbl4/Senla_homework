package com.moon.senla.action.orderAction;

import java.util.ArrayList;
import java.util.List;

import com.moon.senla.action.AbstractAction;
import com.moon.senla.action.Manager;
import com.moon.senla.entity.Book;
import com.moon.senla.entity.Order;
import com.moon.senla.util.CustomException;
import org.springframework.stereotype.Component;

@Component
public class AddOrderAction extends AbstractAction {

    public AddOrderAction(Manager manager) {
        super(manager);
    }

    @Override
    public void execute() {
        String customerName;
        int amount;
        int id;
        List<Book> books = new ArrayList<>();
        System.out.println("Enter the customer's name");
        customerName = customScanner.getString();
        System.out.println("Enter the number of books you want to buy");
        amount = customScanner.getInt();
        for (int i = 0; i < amount; i++) {
            System.out.println("Enter ID of the book you want to buy");
            boolean isValid = true;
            while (isValid) {
                try {
                    id = customScanner.getInt();
                    check.checkBook(id);
                    books.add(manager.getBookService().findBookById(id));
                    if (i == amount - 1) {
                        manager.getOrderService().addOrder(new Order(customerName, books));
                        System.out.println("The order has been added");
                    }
                    isValid = false;
                } catch (CustomException e) {
                    System.out.println(" Value is incorrect");
                }
            }
        }
    }
}

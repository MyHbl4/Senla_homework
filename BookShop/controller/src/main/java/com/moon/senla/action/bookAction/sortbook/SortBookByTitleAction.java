package com.moon.senla.action.bookAction.sortbook;

import java.util.List;

import com.moon.senla.action.AbstractAction;
import com.moon.senla.action.Manager;
import com.moon.senla.entity.Book;
import org.springframework.stereotype.Component;

@Component
public class SortBookByTitleAction extends AbstractAction {

    public SortBookByTitleAction(Manager manager) {
        super(manager);
    }

    @Override
    public void execute() {
        List<Book> sortBooks = manager.getBookService().sortBookByTitle();
        printOut.printList(sortBooks);
    }
}

package com.moon.senla.action.util;

import com.moon.senla.action.Manager;
import com.moon.senla.util.CustomException;

public class CheckOfData {
    public Manager manager;

    public CheckOfData(Manager manager) {
        this.manager = manager;
    }

    public void checkOrder(int id) throws CustomException {
        if (id > 0 && manager.getOrderService().findOrderById(id) != null) {
            return;
        }
        throw new CustomException();
    }

    public void checkBook(int id) throws CustomException {
        if (id > 0 && manager.getBookService().findBookById(id) != null) {
            return;
        }
        throw new CustomException();
    }
}

package com.moon.senla.action.orderAction.sortorder;

import java.util.List;

import com.moon.senla.action.AbstractAction;
import com.moon.senla.action.Manager;
import com.moon.senla.entity.Order;
import org.springframework.stereotype.Component;

@Component
public class SortOrderByExecutionDateAction extends AbstractAction {

    public SortOrderByExecutionDateAction(Manager manager) {
        super(manager);
    }

    @Override
    public void execute() {
        List<Order> sortOrders = manager.getOrderService().sortOrderByExecutionDate();
        printOut.printList(sortOrders);
    }
}

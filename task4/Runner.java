package task4;

import task4.datasource.*;

public class Runner {

    public static void main(String[] args) {
        BookDataSource bookDataSource = new BookDataSourceImpl();
        BookRepository bookRepository = new BookRepositoryImpl(bookDataSource);
        BuyerRepository buyerRepository = new BuyerRepositoryImpl(bookDataSource);
        OrderRepository orderRepository = new OrderrepositoryImpl(bookDataSource);

        bookRepository.printBookRepository();
        buyerRepository.printBuyerRepository();
        orderRepository.printOrderRepository();
    }
}

package task4.datasource;

import task4.model.Buyer;

public class BuyerRepositoryImpl implements BuyerRepository{

    private final BookDataSource bookDataSource;

    public BuyerRepositoryImpl(BookDataSource bookDataSource) {
        this.bookDataSource = bookDataSource;
    }

    @Override
    public void addBuyer(Buyer buyer) {
        System.out.println("Покупатель добавлен!");
        bookDataSource.getBuyers().add(buyer);
    }

    @Override
    public void printBuyerRepository() {
        System.out.println("Список всех покупателей:");
        bookDataSource.getBuyers().print();
    }
}

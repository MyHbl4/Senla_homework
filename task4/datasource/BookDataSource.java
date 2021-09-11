package task4.datasource;

import task4.model.Book;
import task4.other.BookArrayList;
import task4.model.Buyer;
import task4.model.Order;
import task4.other.MyArrayList;

public interface BookDataSource {
BookArrayList<Book> getBooks();
MyArrayList<Order> getOrders();
MyArrayList<Buyer> getBuyers();
}

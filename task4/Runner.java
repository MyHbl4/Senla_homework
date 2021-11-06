package task4;

import java.sql.Array;
import task4.DI.factory.ObjectFactory;
import task4.UI.MenuController;
import task4.jdbc.BookDAO;
import task4.jdbc.OrderDAO;
import task4.jdbc.RequestDAO;
import task4.model.Book;
import task4.model.Order;
import task4.model.Request;

public class Runner {

  public static void main(String[] args) {

//    Book book = new Book("Hobit", "Talkin", 100, 2000);
//    BookDAO bookDAO = new BookDAO();
//    bookDAO.create(book); //создаёт
//    System.out.println(bookDAO.read(1)); //читает
//    System.out.println(bookDAO.readAll());  //читает все
//    bookDAO.update(37); //изменяет
//    bookDAO.delete(39);  //удаляет

//    Order order = new Order("Someone" , '{3, 4}');
//    OrderDAO orderDAO = new OrderDAO();
    //    orderDAO.create(order);  //создаёт
//        System.out.println(orderDAO.readAll()); //читает все
//    System.out.println(orderDAO.readBooks(1));
//    System.out.println(orderDAO.read(1)); //читает
//    orderDAO.update(14); //изменяет
//    orderDAO.delete(13); //удаляет

//    Request request = new Request(36, "TestBook");
//    RequestDAO requestDAO = new RequestDAO();
//    requestDAO.create(request); //создаёт
//    System.out.println(requestDAO.read(1)); //читает
//    System.out.println(requestDAO.readAll());  //читает все
//    requestDAO.update(1); //изменяет
//    requestDAO.delete(2); //удаляет

    MenuController menuController = ObjectFactory.getInstance().createObject(MenuController.class);
    menuController.run();
  }
}
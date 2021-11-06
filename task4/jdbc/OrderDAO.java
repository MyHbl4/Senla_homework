package task4.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import task4.enums.Availability;
import task4.enums.OrderStatus;
import task4.model.Book;
import task4.model.Order;

public class OrderDAO extends AbstractDAO<Integer, Order> {

  public static final String SQL_CREATE_ORDER =
      "INSERT INTO orders (customername) values (?) RETURNING id;";
  public static final String SQL_CREATE_ORDER_BOOKS ="INSERT INTO order_books (order_id, book_id) values ((select max(id) from orders),?) RETURNING id";
  public static final String SQL_READ_ALL_ORDERS =
      "SELECT * FROM orders";
  public static final String SQL_READ_ORDER_ID =
      "SELECT * FROM orders WHERE id=?";
  public static final String SQL_READ_BOOK_IN_ORDER_ID =
      "select o.book_id AS id, books.title, books.author, books.price, books.availability, books.publication, books.deliverydate from order_books o left join books on books.id=o.book_id WHERE o.order_id=?";
  public static final String SQL_UPDATE_ORDER_ID =
      "UPDATE orders SET orderstatus='COMPLETED' WHERE id=? RETURNING id";
  public static final String SQL_UPDATE_CANCEL_ORDER_ID =
      "UPDATE orders SET orderstatus='CANCELED' WHERE id=? RETURNING id";
  public static final String SQL_DELETE_ORDER_ID = "DELETE FROM orders WHERE id=(?) RETURNING id";

  @Override
  public boolean create(Order entity) {
    boolean result = false;

    try (Connection connection = ConnectorDB.getConnection();
        PreparedStatement statement = connection.prepareStatement(SQL_CREATE_ORDER)) {
      statement.setString(1, entity.getCustomerName());
      result = statement.executeQuery().next();
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
    return result;
  }

  public boolean createOrderBooks(Order entity) {
    List<Book> books = entity.getBooks();
    boolean result = false;
    for(Book book:books){
        try (Connection connection = ConnectorDB.getConnection();
        PreparedStatement statement = connection.prepareStatement(SQL_CREATE_ORDER_BOOKS)) {
      statement.setInt(1, Math.toIntExact(book.getId()));
      result = statement.executeQuery().next();
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }}
    return result;
  }

  @Override
  public List<Order> readAll() {
    List<Order> orders = new ArrayList<>();
    try (Connection connection = ConnectorDB.getConnection();
        Statement statement = connection.createStatement()) {
      ResultSet rs = statement.executeQuery(SQL_READ_ALL_ORDERS);
      while (rs.next()) {
        int id = rs.getInt(1);
        String customername = rs.getString(2);
        List<Book> books = readBooks(id);
        OrderStatus orderstatus = OrderStatus.valueOf(rs.getString(3));
        LocalDate execution = LocalDate.parse(rs.getString(4));
        orders.add(new Order(id, customername, books,  orderstatus, execution));
      }
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
    return orders;
  }

  @Override
  public Order read(Integer id) {
    Order order = null;
    try (Connection connection = ConnectorDB.getConnection();
        PreparedStatement statement = connection.prepareStatement(SQL_READ_ORDER_ID)) {
      statement.setInt(1, id);
      ResultSet rs = statement.executeQuery();
      if (rs.next()) {
        String customername = rs.getString(2);
        List<Book> books = readBooks(id);
        OrderStatus orderstatus = OrderStatus.valueOf(rs.getString(3));
        LocalDate execution = LocalDate.parse(rs.getString(4));
        order = new Order(id, customername, books, orderstatus, execution);
      }
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
    return order;
  }

  public List<Book> readBooks(Integer order_id) {
    List<Book> books = new ArrayList<>();
    try (Connection connection = ConnectorDB.getConnection();
        PreparedStatement statement = connection.prepareStatement(SQL_READ_BOOK_IN_ORDER_ID)) {
      statement.setInt(1, order_id);
      ResultSet rs = statement.executeQuery();
      while (rs.next()) {
        int id = rs.getInt(1);
        String title = rs.getString(2);
        String author = rs.getString(3);
        int price = rs.getInt(4);
        int publication = rs.getInt(6);
        Availability availability = Availability.valueOf(rs.getString(5));
        LocalDate deliverydate = LocalDate.parse(rs.getString(7));
        books.add(new Book(id, title, author, price, publication, availability, deliverydate));
      }
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
    return books;
  }

  @Override
  public boolean update(Integer id) {
    boolean result = false;

    try (Connection connection = ConnectorDB.getConnection();
        PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_ORDER_ID)) {
      statement.setInt(1, id);
      result = statement.executeQuery().next();
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
    return result;
  }

  public boolean updateCancel(Integer id) {
    boolean result = false;

    try (Connection connection = ConnectorDB.getConnection();
        PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_CANCEL_ORDER_ID)) {
      statement.setInt(1, id);
      result = statement.executeQuery().next();
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
    return result;
  }

  @Override
  public boolean delete(Integer id) {
    boolean result = false;

    try (Connection connection = ConnectorDB.getConnection();
        PreparedStatement statement = connection.prepareStatement(SQL_DELETE_ORDER_ID)) {
      statement.setInt(1, id);
      result = statement.executeQuery().next();
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
    return result;
  }
}

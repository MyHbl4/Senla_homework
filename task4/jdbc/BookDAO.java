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
import task4.model.Book;

public class BookDAO extends AbstractDAO<Integer, Book> {

  public static final String SQL_CREATE_BOOK =
      "INSERT INTO books (title, author, price, publication) values (?, ?, ?, ?) RETURNING id";
  public static final String SQL_READ_ALL_BOOKS = "SELECT * FROM books";
  public static final String SQL_READ_BOOK_ID = "SELECT * FROM books WHERE id=?";
  public static final String SQL_UPDATE_BOOK_ID =
      "UPDATE books SET availability='OUT_OF_STOCK' WHERE id=(?) RETURNING id";
  public static final String SQL_UPDATE_IN_BOOK_ID =
      "UPDATE books SET availability='IN_STOCK' WHERE id=(?) RETURNING id";
  public static final String SQL_UPDATE_DELIVERY_BOOK_ID =
      "UPDATE books SET deliverydate=NOW() WHERE id=(?) RETURNING id";
  public static final String SQL_DELETE_BOOK_ID = "DELETE FROM books WHERE id=(?) RETURNING id";

  @Override
  public boolean create(Book entity) {
    boolean result = false;

    try (Connection connection = ConnectorDB.getConnection();
        PreparedStatement statement = connection.prepareStatement(SQL_CREATE_BOOK)) {
      statement.setString(1, entity.getTitle());
      statement.setString(2, entity.getAuthor());
      statement.setInt(3, entity.getPrice());
      statement.setInt(4, entity.getPublication());
      result = statement.executeQuery().next();
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
    return result;
  }

  @Override
  public List<Book> readAll() {
    List<Book> books = new ArrayList<>();
    try (Connection connection = ConnectorDB.getConnection();
        Statement statement = connection.createStatement()) {
      ResultSet rs = statement.executeQuery(SQL_READ_ALL_BOOKS);
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
  public Book read(Integer id) {
    Book book = null;
    try (Connection connection = ConnectorDB.getConnection();
        PreparedStatement statement = connection.prepareStatement(SQL_READ_BOOK_ID)) {
      statement.setInt(1, id);
      ResultSet rs = statement.executeQuery();
      if (rs.next()) {
        String title = rs.getString(2);
        String author = rs.getString(3);
        int price = rs.getInt(4);
        int publication = rs.getInt(6);
        Availability availability = Availability.valueOf(rs.getString(5));
        LocalDate deliverydate = LocalDate.parse(rs.getString(7));
        book = new Book(id, title, author, price, publication, availability, deliverydate);
      }
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
    return book;
  }

  @Override
  public boolean update(Integer id) {
    boolean result = false;

    try (Connection connection = ConnectorDB.getConnection();
        PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_BOOK_ID)) {
      statement.setInt(1, id);
      result = statement.executeQuery().next();
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
    return result;
  }

  public boolean updateIN(Integer id) {
    boolean result = false;

    try (Connection connection = ConnectorDB.getConnection();
        PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_IN_BOOK_ID)){
      statement.setInt(1, id);
      result = statement.executeQuery().next();
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
    return result;
  }

  public boolean updateInAndDate(Integer id) {
    boolean result = false;
    try (Connection connection = ConnectorDB.getConnection();
        PreparedStatement statement1 = connection.prepareStatement(SQL_UPDATE_IN_BOOK_ID);
        PreparedStatement statement2 = connection.prepareStatement(SQL_UPDATE_DELIVERY_BOOK_ID)) {
      connection.setAutoCommit(false);
      statement1.setInt(1, id);
      statement2.setInt(1, id);
      connection.commit();
      connection.setAutoCommit(true);
      result = statement2.executeQuery().next();
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
    return result;
  }

  //  public boolean updateInAndDate(Integer id){
  //    boolean result = false;
  //    try(Connection connection = ConnectorDB.getConnection();
  //    PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_IN_BOOK_ID)){
  //      connection.setAutoCommit(false);
  //      statement.setInt(1, id);
  //      statement.addBatch();
  //      statement.setInt(1,id);
  //      statement.executeBatch();
  //      connection.commit();
  //      connection.rollback();
  //      connection.setAutoCommit(true);
  //    } catch (SQLException throwables) {
  //      throwables.printStackTrace();
  //    }
  //    return result;
  //  }

  public boolean updateDelivery(Integer id) {
    boolean result = false;

    try (Connection connection = ConnectorDB.getConnection();
        PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_DELIVERY_BOOK_ID)) {
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
        PreparedStatement statement = connection.prepareStatement(SQL_DELETE_BOOK_ID)) {
      statement.setInt(1, id);
      result = statement.executeQuery().next();
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
    return result;
  }
}

package com.moon.senla;

import com.moon.senla.entity.Book;
import com.moon.senla.enums.Availability;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BookDAO extends AbstractDAO<Integer, Book> {
  private static final Logger logger = LoggerFactory.getLogger(BookDAO.class);

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
      logger.info("Method completed - " + Thread.currentThread().getStackTrace()[1].getMethodName());
    } catch (SQLException e) {
      logger.warn("Failed to execute the method - " + Thread.currentThread().getStackTrace()[1].getMethodName(), e);
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
      logger.info("Method completed - " + Thread.currentThread().getStackTrace()[1].getMethodName());
    } catch (SQLException e) {
      logger.warn("Failed to execute the method - " + Thread.currentThread().getStackTrace()[1].getMethodName(), e);
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
      logger.info("Method completed - " + Thread.currentThread().getStackTrace()[1].getMethodName());
    } catch (SQLException e) {
      logger.warn("Failed to execute the method - " + Thread.currentThread().getStackTrace()[1].getMethodName(), e);
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
      logger.info("Method completed - " + Thread.currentThread().getStackTrace()[1].getMethodName());
    } catch (SQLException e) {
      logger.warn("Failed to execute the method - " + Thread.currentThread().getStackTrace()[1].getMethodName(), e);
    }
    return result;
  }

//  public boolean updateIN(Integer id) {
//    boolean result = false;
//
//    try (Connection connection = ConnectorDB.getConnection();
//        PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_IN_BOOK_ID)) {
//      statement.setInt(1, id);
//      result = statement.executeQuery().next();
//    } catch (SQLException e) {
//      System.out.println(e.getMessage());
//    }
//    return result;
//  }

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
      logger.info("Method completed - " + Thread.currentThread().getStackTrace()[1].getMethodName());
    } catch (SQLException e) {
      logger.warn("Failed to execute the method - " + Thread.currentThread().getStackTrace()[1].getMethodName(), e);
    }
    return result;
  }

//  public boolean updateDelivery(Integer id) {
//    boolean result = false;
//
//    try (Connection connection = ConnectorDB.getConnection();
//        PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_DELIVERY_BOOK_ID)) {
//      statement.setInt(1, id);
//      result = statement.executeQuery().next();
//    } catch (SQLException e) {
//      System.out.println(e.getMessage());
//    }
//    return result;
//  }

  @Override
  public boolean delete(Integer id) {
    boolean result = false;

    try (Connection connection = ConnectorDB.getConnection();
        PreparedStatement statement = connection.prepareStatement(SQL_DELETE_BOOK_ID)) {
      statement.setInt(1, id);
      result = statement.executeQuery().next();
      logger.info("Method completed - " + Thread.currentThread().getStackTrace()[1].getMethodName());
    } catch (SQLException e) {
      logger.warn("Failed to execute the method - " + Thread.currentThread().getStackTrace()[1].getMethodName(), e);
    }
    return result;
  }
}

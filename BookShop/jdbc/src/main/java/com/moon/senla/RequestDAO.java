package com.moon.senla;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RequestDAO extends AbstractDAO<Integer, Request> {

  public static final String SQL_CREATE_REQUESTS =
      "INSERT INTO requests (book_id, book_title) values (?, ?) RETURNING id";
  public static final String SQL_READ_ALL_REQUESTS = "SELECT * FROM requests";
  public static final String SQL_READ_REQUEST_ID = "SELECT * FROM requests WHERE id=?";
  public static final String SQL_UPDATE_REQUEST_ID =
      "UPDATE requests SET count=((SELECT SUM(count) FROM requests WHERE id=?)+1) WHERE id=?";
  public static final String SQL_DELETE_REQUEST_ID =
      "DELETE FROM requests WHERE id=(?) RETURNING id";

  @Override
  public boolean create(Request entity) {
    boolean result = false;

    try (Connection connection = ConnectorDB.getConnection();
        PreparedStatement statement = connection.prepareStatement(SQL_CREATE_REQUESTS)) {
      statement.setInt(1, Math.toIntExact(entity.getBookId()));
      statement.setString(2, entity.getTitle());
      result = statement.executeQuery().next();
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
    return result;
  }

  @Override
  public List<Request> readAll() {
    List<Request> requests = new ArrayList<>();
    try (Connection connection = ConnectorDB.getConnection();
        Statement statement = connection.createStatement()) {
      ResultSet rs = statement.executeQuery(SQL_READ_ALL_REQUESTS);
      while (rs.next()) {
        int id = rs.getInt(1);
        int count = rs.getInt(2);
        int book_id = rs.getInt(3);
        String book_title = rs.getString(4);
        requests.add(new Request(id, count, book_id, book_title));
      }
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
    return requests;
  }

  @Override
  public Request read(Integer id) {
    Request request = null;
    try (Connection connection = ConnectorDB.getConnection();
        PreparedStatement statement = connection.prepareStatement(SQL_READ_REQUEST_ID)) {
      statement.setInt(1, id);
      ResultSet rs = statement.executeQuery();
      if (rs.next()) {
        int count = rs.getInt(2);
        int book_id = rs.getInt(3);
        String book_title = rs.getString(4);
        request = new Request(id, count, book_id, book_title);
      }
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
    return request;
  }

  @Override
  public boolean update(Integer id) {
    boolean result = false;

    try (Connection connection = ConnectorDB.getConnection();
        PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_REQUEST_ID)) {
      statement.setInt(1, id);
      statement.setInt(2, id);
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
        PreparedStatement statement = connection.prepareStatement(SQL_DELETE_REQUEST_ID)) {
      statement.setInt(1, id);
      result = statement.executeQuery().next();
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
    return result;
  }
}

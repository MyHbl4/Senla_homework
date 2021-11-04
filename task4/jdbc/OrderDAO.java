package task4.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import task4.enums.OrderStatus;
import task4.model.Order;

public class OrderDAO extends AbstractDAO<Integer, Order> {

  public static final String SQL_CREATE_ORDER =
      "INSERT INTO orders (customername) values (?) RETURNING id";
  public static final String SQL_READ_ALL_ORDERS = "SELECT * FROM orders";
  public static final String SQL_READ_ORDER_ID = "SELECT * FROM orders WHERE id=?";
  public static final String SQL_UPDATE_ORDER_ID =
      "UPDATE orders SET orderstatus='COMPLETED' WHERE id=? RETURNING id";
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

  @Override
  public List<Order> readAll() {
    List<Order> orders = new ArrayList<>();
    try (Connection connection = ConnectorDB.getConnection();
        Statement statement = connection.createStatement()) {
      ResultSet rs = statement.executeQuery(SQL_READ_ALL_ORDERS);
      while (rs.next()) {
        int id = rs.getInt(1);
        String customername = rs.getString(2);
        OrderStatus orderstatus = OrderStatus.valueOf(rs.getString(3));
        LocalDate execution = LocalDate.parse(rs.getString(4));
        orders.add(new Order(id, customername, orderstatus, execution));
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
        OrderStatus orderstatus = OrderStatus.valueOf(rs.getString(3));
        LocalDate execution = LocalDate.parse(rs.getString(4));
        order = new Order(id, customername, orderstatus, execution);
      }
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
    return order;
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

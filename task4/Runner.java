package task4;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import task4.DI.factory.ObjectFactory;
import task4.UI.MenuController;

public class Runner {

  public static void main(String[] args) throws SQLException {
    final String user = "postgres";
    final String password = "1988";
    final String url = "jdbc:postgresql://localhost:5432/book_shop";

    final Connection connection = DriverManager.getConnection(url, user, password);

    try (PreparedStatement statement = connection.prepareStatement(
        "SELECT * FROM requests WHERE id = (?)")) {
      statement.setInt( 1, 1);

      final ResultSet resultSet = statement.executeQuery();

      if(resultSet.next()){
        String name = "Name: " + resultSet.getString("book_title");
        System.out.println(name);
      }
    } finally{
      connection.close();
    }




//    MenuController menuController = ObjectFactory.getInstance().createObject(MenuController.class);
//    menuController.run();
  }
}
